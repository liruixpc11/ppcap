package lab.cadl.lirui.ppcap.core.packet;

import java.nio.ByteBuffer;

/**
 *
 */
public abstract class AbstractPacket<THeader extends Header> implements Packet<THeader> {
    private THeader header;
    private Packet payload;
    private ByteBuffer rawPayload;
    private int length;

    public AbstractPacket(THeader header, ByteBuffer rawPayload, int length) {
        this.header = header;
        this.rawPayload = rawPayload;
        this.length = length;
    }

    @Override
    public THeader header() {
        return header;
    }

    @Override
    public Packet payload() {
        return payload;
    }

    @Override
    public int length() {
        return length;
    }

    @Override
    public ByteBuffer rawData() {
        return rawPayload;
    }

    public Packet parsePayload() {
        if (payload == null) {
            payload = doParsePayload();
        }

        return payload;
    }

    protected abstract Packet doParsePayload();
}
