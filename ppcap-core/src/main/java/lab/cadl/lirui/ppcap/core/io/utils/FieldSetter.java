package lab.cadl.lirui.ppcap.core.io.utils;

import java.nio.ByteBuffer;

/**
 *
 */
@FunctionalInterface
public interface FieldSetter<T> {
    void set(T structure, ByteBuffer buffer, int base) throws Exception;
}
