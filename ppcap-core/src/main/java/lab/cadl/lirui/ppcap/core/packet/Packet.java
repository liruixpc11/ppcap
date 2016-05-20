package lab.cadl.lirui.ppcap.core.packet;

/**
 *
 */
public interface Packet<THeader> extends RawData {
    String type();
    THeader header();
    Packet payload();

    default boolean isEmpty() {
        return false;
    }
}
