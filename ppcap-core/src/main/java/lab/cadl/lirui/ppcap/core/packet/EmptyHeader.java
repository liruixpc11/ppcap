package lab.cadl.lirui.ppcap.core.packet;

/**
 *
 */
public class EmptyHeader extends EmptyRawData implements Header {
    private static final EmptyHeader instance = new EmptyHeader();

    protected EmptyHeader() {}

    public static EmptyHeader getInstance() {
        return instance;
    }
}
