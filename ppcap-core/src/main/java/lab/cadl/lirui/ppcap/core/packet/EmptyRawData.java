package lab.cadl.lirui.ppcap.core.packet;

import java.nio.ByteBuffer;

/**
 *
 */
public class EmptyRawData implements RawData {
    private static final ByteBuffer empty = ByteBuffer.wrap(new byte[0]);

    public int length() {
        return 0;
    }

    public ByteBuffer rawData() {
        return empty;
    }
}
