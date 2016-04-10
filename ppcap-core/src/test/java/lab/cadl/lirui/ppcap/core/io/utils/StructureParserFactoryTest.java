package lab.cadl.lirui.ppcap.core.io.utils;

import lab.cadl.lirui.ppcap.core.io.structures.PcapHeaderStructure;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 */
public class StructureParserFactoryTest {

    @Test
    public void testCreateParser() throws Exception {
        assertNotNull(StructureParserFactory.getInstance().createParser(PcapHeaderStructure.class));
    }

    @Test
    public void testGetParser() throws Exception {
        StructureParser<PcapHeaderStructure> parser = StructureParserFactory.getInstance().getParser(PcapHeaderStructure.class);
        assertNotNull(parser);
        assertEquals(24, parser.length());
    }
}