package lab.cadl.lirui.ppcap.core.packet;

import java.nio.ByteBuffer;

/**
 *
 */
public interface RawData {
    int length();
    ByteBuffer rawData();
}
