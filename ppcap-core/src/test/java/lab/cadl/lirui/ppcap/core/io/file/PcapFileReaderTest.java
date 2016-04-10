package lab.cadl.lirui.ppcap.core.io.file;

import lab.cadl.lirui.ppcap.core.io.PacketReader;
import lab.cadl.lirui.ppcap.core.packet.Packet;
import lab.cadl.lirui.ppcap.core.packet.RawPacket;
import org.junit.Test;

import java.nio.file.Paths;

import static org.junit.Assert.*;

/**
 *
 */
public class PcapFileReaderTest {
    @Test
    public void testNext() throws Exception {
        try (PcapFileReader reader = new ChannelPcapFileReader(Paths.get("../data/LLS_DDOS_2.0.2-inside.dump.pcap"))) {
            while (reader.hasNext()) {
                RawPacket packet = reader.next();
                assertNotNull(packet);
            }
        }
    }
}