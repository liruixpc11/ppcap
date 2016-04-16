package lab.cadl.lirui.ppcap.core.packet;

import java.nio.ByteBuffer;

/**
 *
 */
public class EmptyRawData implements RawData {
    private static final ByteBuffer empty = ByteBuffer.wrap(new byte[0]);

    @Override
    public int length() {
        return 0;
    }

    @Override
    public ByteBuffer rawData() {
        return empty;
    }

    @Override
    public int begin() {
        return 0;
    }
}
