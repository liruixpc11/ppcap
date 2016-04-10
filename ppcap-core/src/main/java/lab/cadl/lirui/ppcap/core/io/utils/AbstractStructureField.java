package lab.cadl.lirui.ppcap.core.io.utils;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;

/**
 *
 */
public abstract class AbstractStructureField<T> implements StructureField<T> {
    final int begin;
    final int length;

    public AbstractStructureField(int begin, int length) {
        this.begin = begin;
        this.length = length;
    }

    @Override
    public int length() {
        return length;
    }

    @Override
    public int begin() {
        return begin;
    }

    @Override
    public int end() {
        return begin + length;
    }

    @Override
    public final void parseTo(T structure, ByteBuffer buffer, int sBegin, int sLength) {
        if (this.begin + this.length > sLength) {
            throw new IndexOutOfBoundsException();
        }

        parseTo(structure, buffer, sBegin);
    }

    protected abstract void parseTo(T structure, ByteBuffer buffer, int sBegin);
}
