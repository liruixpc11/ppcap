package lab.cadl.lirui.ppcap.core.io.utils;

import java.nio.ByteBuffer;

/**
 *
 */
public interface StructureField<T> {
    void parseTo(T structure, ByteBuffer buffer, int sBegin, int sLength);
    int length();
    int begin();
    int end();
}
