package lab.cadl.lirui.ppcap.core.packet;

/**
 *
 */
public class EmptyPacket extends EmptyRawData implements Packet {
    private static final EmptyPacket instance = new EmptyPacket();

    public static EmptyPacket getInstance() {
        return instance;
    }

    @Override
    public String type() {
        return "Empty";
    }

    public Header header() {
        return EmptyHeader.getInstance();
    }

    public Packet payload() {
        return getInstance();
    }

    @Override
    public boolean isEmpty() {
        return true;
    }
}
