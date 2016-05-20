package lab.cadl.lirui.ppcap.core.packet.ethernet;

import lab.cadl.lirui.ppcap.core.io.annotations.Field;
import lab.cadl.lirui.ppcap.core.io.annotations.Structure;
import lab.cadl.lirui.ppcap.core.io.utils.SelfParser;
import lab.cadl.lirui.ppcap.core.io.utils.StructureParser;

import java.nio.ByteBuffer;

/**
 *
 */
@Structure
public class MacAddress implements SelfParser<MacAddress> {
    public static final int LENGTH = 6;

    @Field(length = LENGTH)
    private byte[] bytes = new byte[LENGTH];

    public MacAddress() {
    }

    public MacAddress(String macString) {
        String[] bytesString = macString.split(":");
        if (bytesString.length != LENGTH) {
            throw new IllegalArgumentException("mac address length is " + LENGTH + " but not " + bytesString.length);
        }

        for (int i = 0; i < LENGTH; i++) {
            bytes[i] = Byte.parseByte(bytesString[i], 16);
        }
    }

    public byte[] getBytes() {
        return bytes;
    }

    @Override
    public String toString() {
        return String.format("%02X:%02X:%02X:%02X:%02X:%02X", bytes[0], bytes[1], bytes[2], bytes[3], bytes[4], bytes[5]);
    }

    /**
     * optimization
     */
    @Override
    public int parse(StructureParser<MacAddress> parser, ByteBuffer buffer, int begin, int length) {
        buffer.position(begin);
        buffer.get(bytes);
        return LENGTH;
    }
}
