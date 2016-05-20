package lab.cadl.lirui.ppcap.core.packet;

import java.nio.ByteBuffer;

/**
 *
 */
public abstract class AbstractPacket<THeader extends Header> extends AbstractRawData implements Packet<THeader> {
    private THeader header;
    private Packet payload;

    public AbstractPacket(THeader header, ByteBuffer rawPayload, int begin, int length) {
        super(rawPayload, begin, length);
        this.header = header;
    }

    @Override
    public String type() {
        String name = getClass().getSimpleName();
        if (name.endsWith("Packet")) {
            return name.substring(0, name.length() - 6);
        } else {
            return name;
        }
    }

    @Override
    public THeader header() {
        return header;
    }

    @Override
    public Packet payload() {
        if (payload == null) {
            payload = doParsePayload();
        }

        return payload;
    }

    protected abstract Packet doParsePayload();
}
