package lab.cadl.lirui.ppcap.core.packet;

/**
 *
 */
public interface Packet extends RawData {
    Header header();
    Packet payload();

    default boolean isEmpty() {
        return false;
    }
}
