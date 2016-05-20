package lab.cadl.lirui.ppcap.core.packet.ethernet;

import lab.cadl.lirui.ppcap.core.io.annotations.Field;
import lab.cadl.lirui.ppcap.core.io.annotations.Structure;
import lab.cadl.lirui.ppcap.core.io.utils.PacketBuilder;
import lab.cadl.lirui.ppcap.core.io.utils.SelfParser;
import lab.cadl.lirui.ppcap.core.io.utils.StructureParser;
import lab.cadl.lirui.ppcap.core.io.utils.StructureParserFactory;
import lab.cadl.lirui.ppcap.core.packet.AbstractHeader;

import java.nio.ByteBuffer;

/**
 *
 */
@Structure
public class EthernetHeader extends AbstractHeader implements SelfParser<EthernetHeader> {
    @Field
    private MacAddress targetAddress = new MacAddress();
    @Field
    private MacAddress sourceAddress = new MacAddress();
    @Field
    private short length;

    @Override
    public String toString() {
        return "EthernetHeader{" +
                "targetAddress=" + targetAddress +
                ", sourceAddress=" + sourceAddress +
                ", length=" + length +
                '}';
    }

    @Override
    public int parse(StructureParser<EthernetHeader> parser, ByteBuffer buffer, int begin, int length) {
        StructureParser<MacAddress> macParser = StructureParserFactory.p(MacAddress.class);
        targetAddress.parse(macParser, buffer, begin, macParser.length());
        sourceAddress.parse(macParser, buffer, begin + macParser.length(), macParser.length());
        this.length = buffer.getShort(begin + 2 * macParser.length());
        return parser.length();
    }
}
