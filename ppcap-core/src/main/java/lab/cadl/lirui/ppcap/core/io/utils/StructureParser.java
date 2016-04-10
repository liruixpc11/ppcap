package lab.cadl.lirui.ppcap.core.io.utils;

import lab.cadl.lirui.ppcap.core.utils.Utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class StructureParser<T> {
    private Class<T> tClass;
    private List<StructureField<T>> fields = new ArrayList<>();
    private boolean bigEndian;
    private int length = 0;

    public StructureParser(Class<T> tClass) {
        this.tClass = tClass;
    }

    StructureParser<T> field(StructureField<T> field) {
        fields.add(field);
        if (field.end() > length) {
            length = field.end();
        }

        return this;
    }

    public void setBigEndian(boolean bigEndian) {
        this.bigEndian = bigEndian;
    }

    public T parse(ByteBuffer buffer, int begin, int length) {
        try {
            Constructor<T> constructor = tClass.getConstructor();
            T structure = constructor.newInstance();
            return parse(structure, buffer, begin, length);
        } catch (InvocationTargetException e) {
            throw Utils.wrap(e.getCause());
        } catch (Exception e) {
            throw Utils.wrap(e);
        }
    }

    @SuppressWarnings("unchecked")
    public T parse(T structure, ByteBuffer buffer, int begin, int length) {
        boolean changed = false;
        if (bigEndian != (buffer.order() == ByteOrder.BIG_ENDIAN)) {
            buffer.order(bigEndian ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
            changed = true;
        }

        if (structure instanceof SelfParser) {
            ((SelfParser<T>) structure).parse(this, buffer, begin, length);
        } else {
            for (StructureField<T> field : fields) {
                field.parseTo(structure, buffer, begin, length);
            }
        }

        if (structure instanceof PacketBuilder) {
            ((PacketBuilder) structure).build();
        }

        if (changed) {
            buffer.order(!bigEndian ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        }

        return structure;
    }

    public int length() {
        return length;
    }
}
