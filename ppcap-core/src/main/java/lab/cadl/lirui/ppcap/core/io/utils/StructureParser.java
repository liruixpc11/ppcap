package lab.cadl.lirui.ppcap.core.io.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class StructureParser<T> {
    private Class<T> tClass;
    private List<StructureField<T>> fields = new ArrayList<>();

    public StructureParser(Class<T> tClass) {
        this.tClass = tClass;
    }

    StructureParser<T> field(StructureField<T> field) {
        fields.add(field);
        return this;
    }

    public T parse(ByteBuffer buffer, int begin, int length) {
        try {
            Constructor<T> constructor = tClass.getConstructor();
            T structure = constructor.newInstance();
            for (StructureField<T> field : fields) {
                field.parseTo(structure, buffer, begin, length);
            }

            return structure;
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e.getCause());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int length() {
        int length = 0;
        for (StructureField field : fields) {
            if (field.end() > length) {
                length = field.end();
            }
        }

        return length;
    }
}
