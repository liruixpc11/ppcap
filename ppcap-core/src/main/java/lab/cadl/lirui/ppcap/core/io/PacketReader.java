package lab.cadl.lirui.ppcap.core.io;

import lab.cadl.lirui.ppcap.core.packet.Packet;

import java.io.Closeable;
import java.util.Iterator;

/**
 *
 */
public interface PacketReader extends Iterator<Packet>, AutoCloseable {
    @Override
    void close();
}
