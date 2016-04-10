package lab.cadl.lirui.ppcap.core.packet;

import lab.cadl.lirui.ppcap.core.io.structures.PacketHeaderStructure;
import lab.cadl.lirui.ppcap.core.io.structures.PcapHeaderStructure;

import java.nio.ByteBuffer;

/**
 *
 */
public class RawPacket extends AbstractPacket<PacketHeaderStructure> {
    public RawPacket(PacketHeaderStructure header, ByteBuffer rawPayload) {
        super(header, rawPayload, header.getIncludedLength());
    }

    @Override
    protected Packet doParsePayload() {
        return null;
    }
}
