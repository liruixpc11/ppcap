package lab.cadl.lirui.ppcap.core.io.utils;

import lab.cadl.lirui.ppcap.core.utils.Utils;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;

/**
 *
 */
public class ArrayStructureField<T> extends AbstractStructureField<T> {
    private Field field;
    private StructureParser elementParser;
    private int size;

    public ArrayStructureField(int begin, int length, int size, Field field, StructureParser elementParser) {
        super(begin, length);
        this.field = field;
        this.elementParser = elementParser;
        this.size = size;
    }

    @Override
    protected void parseTo(T structure, ByteBuffer buffer, int sBegin) {
        Object array = Array.newInstance(field.getType().getComponentType(), size);
        for (int i = 0; i < size; i++) {
            Array.set(array, i, elementParser.parse(buffer, begin + i * elementParser.length(), elementParser.length()));
        }

        try {
            field.set(structure, array);
        } catch (IllegalAccessException e) {
            throw Utils.wrap(e);
        }
    }
}
