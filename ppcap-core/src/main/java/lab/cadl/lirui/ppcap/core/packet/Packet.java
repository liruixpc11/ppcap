package lab.cadl.lirui.ppcap.core.packet;

/**
 *
 */
public interface Packet<THeader> extends RawData {
    THeader header();
    Packet payload();

    default boolean isEmpty() {
        return false;
    }
}
