package lab.cadl.lirui.ppcap.core.packet;

import lab.cadl.lirui.ppcap.core.io.structures.PacketHeaderStructure;
import lab.cadl.lirui.ppcap.core.io.structures.PcapHeaderStructure;
import lab.cadl.lirui.ppcap.core.io.utils.StructureParser;
import lab.cadl.lirui.ppcap.core.io.utils.StructureParserFactory;
import lab.cadl.lirui.ppcap.core.packet.ethernet.EthernetHeader;
import lab.cadl.lirui.ppcap.core.packet.ethernet.EthernetPacket;

import java.nio.ByteBuffer;

/**
 *
 */
public class RawPacket extends AbstractPacket<PacketHeaderStructure> {
    private static final StructureParser<EthernetHeader> ethernetHeaderParser = StructureParserFactory.p(EthernetHeader.class);

    public RawPacket(PacketHeaderStructure header, ByteBuffer rawPayload) {
        super(header, rawPayload, 0, header.getIncludedLength());
    }

    @Override
    protected Packet doParsePayload() {
        EthernetHeader header = ethernetHeaderParser.parse(rawData(), begin(), ethernetHeaderParser.length());
        return new EthernetPacket(header, rawData(), begin() + ethernetHeaderParser.length(), length() - ethernetHeaderParser.length());
    }
}
