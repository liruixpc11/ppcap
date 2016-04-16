package lab.cadl.lirui.ppcap.core.packet;

import java.nio.ByteBuffer;

/**
 *
 */
public abstract class AbstractHeader extends AbstractRawData implements Header {
    public AbstractHeader(ByteBuffer rawData, int begin, int length) {
        super(rawData, begin, length);
    }



    public AbstractHeader() {}
}
