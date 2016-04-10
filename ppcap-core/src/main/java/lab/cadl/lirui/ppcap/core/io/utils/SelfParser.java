package lab.cadl.lirui.ppcap.core.io.utils;

import java.nio.ByteBuffer;

/**
 * parse ByteBuffer by self
 */
public interface SelfParser<T> {
    /**
     * parse buffer without reflection for performance
     *
     * @param parser parser
     * @param buffer buffer
     * @param begin begin index of buffer
     * @param length buffer length
     */
    void parse(StructureParser<T> parser, ByteBuffer buffer, int begin, int length);
}
