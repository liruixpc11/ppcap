package lab.cadl.lirui.ppcap.core.packet;

import java.nio.ByteBuffer;

/**
 *
 */
public interface RawData {
    ByteBuffer rawData();
    int begin();
    int length();
}
