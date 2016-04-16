package lab.cadl.lirui.ppcap.core.packet.ethernet;

import lab.cadl.lirui.ppcap.core.io.annotations.Field;
import lab.cadl.lirui.ppcap.core.io.annotations.Structure;
import lab.cadl.lirui.ppcap.core.io.utils.PacketBuilder;
import lab.cadl.lirui.ppcap.core.io.utils.SelfParser;
import lab.cadl.lirui.ppcap.core.io.utils.StructureParser;
import lab.cadl.lirui.ppcap.core.packet.AbstractHeader;

import java.nio.ByteBuffer;

/**
 *
 */
@Structure
public class EthernetHeader extends AbstractHeader implements PacketBuilder {
    @Field
    private MacAddress targetAddress;
    @Field
    private MacAddress sourceAddress;
    @Field
    private short length;

    @Override
    public void build() {
    }

    @Override
    public String toString() {
        return "EthernetHeader{" +
                "targetAddress=" + targetAddress +
                ", sourceAddress=" + sourceAddress +
                ", length=" + length +
                '}';
    }
}
