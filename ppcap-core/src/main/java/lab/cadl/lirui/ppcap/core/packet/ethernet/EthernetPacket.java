package lab.cadl.lirui.ppcap.core.packet.ethernet;

import lab.cadl.lirui.ppcap.core.packet.AbstractPacket;
import lab.cadl.lirui.ppcap.core.packet.Packet;

import java.nio.ByteBuffer;

/**
 *
 */
public class EthernetPacket extends AbstractPacket<EthernetHeader> {
    public EthernetPacket(EthernetHeader header, ByteBuffer rawPayload, int begin, int length) {
        super(header, rawPayload, begin, length);
    }

    @Override
    protected Packet doParsePayload() {
        return null;
    }
}
