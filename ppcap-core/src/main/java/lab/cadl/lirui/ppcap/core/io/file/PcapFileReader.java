package lab.cadl.lirui.ppcap.core.io.file;

import lab.cadl.lirui.ppcap.core.io.PacketReader;
import lab.cadl.lirui.ppcap.core.packet.Packet;

/**
 *
 */
public class PcapFileReader implements PacketReader {


    public PcapFileReader(String filename) {
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Packet next() {
        return null;
    }

    @Override
    public void close() {

    }
}
