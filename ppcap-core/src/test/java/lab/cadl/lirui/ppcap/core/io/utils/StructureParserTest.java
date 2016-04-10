package lab.cadl.lirui.ppcap.core.io.utils;

import lab.cadl.lirui.ppcap.core.io.structures.PcapHeaderStructure;
import org.junit.Test;

import java.nio.ByteBuffer;

import static org.junit.Assert.*;

/**
 *
 */
public class StructureParserTest {
    private ByteBuffer pcap = ByteBuffer.wrap(new byte[] {
            (byte) 0xa1, (byte) 0xb2, (byte) 0xc3, (byte) 0xd4,
            (byte) 0x00, (byte) 0x02, (byte) 0x00, (byte) 0x04,
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
            (byte) 0x00, (byte) 0x01, (byte) 0x01, (byte) 0xd0,
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x01
    });

    @Test
    public void testParse() throws Exception {
        StructureParser<PcapHeaderStructure> parser = StructureParserFactory.getInstance().getParser(PcapHeaderStructure.class);
        PcapHeaderStructure structure = parser.parse(pcap, 0, pcap.array().length);
        assertEquals(0xa1b2c3d4, structure.getMagicNumber());
        assertEquals(2, structure.getVersionMajor());
        assertEquals(4, structure.getVersionMinor());
        assertEquals(0, structure.getZone());
        assertEquals(0, structure.getReserved());
        assertEquals(0x0101d0, structure.getSnapLength());
        assertEquals(1, structure.getNetwork());
    }
}