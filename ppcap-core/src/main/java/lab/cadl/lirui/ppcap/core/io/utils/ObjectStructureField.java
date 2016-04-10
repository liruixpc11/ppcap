package lab.cadl.lirui.ppcap.core.io.utils;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;

/**
 *
 */
public class ObjectStructureField<T> extends AbstractStructureField<T> {
    private StructureParser parser;
    private Field field;

    public ObjectStructureField(int begin, int length, Field field, StructureParser parser) {
        super(begin, length);
        this.field = field;
        this.parser = parser;
    }

    @Override
    public void parseTo(Object structure, ByteBuffer buffer, int sBegin) {
        Object value = parser.parse(buffer, this.begin + sBegin, this.length);
        try {
            field.set(structure, value);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
