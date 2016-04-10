package lab.cadl.lirui.ppcap.core.io.structures;

import lab.cadl.lirui.ppcap.core.io.annotations.Field;
import lab.cadl.lirui.ppcap.core.io.annotations.Structure;
import lab.cadl.lirui.ppcap.core.io.utils.SelfParser;
import lab.cadl.lirui.ppcap.core.io.utils.StructureParser;
import lab.cadl.lirui.ppcap.core.packet.EmptyHeader;
import lab.cadl.lirui.ppcap.core.packet.Header;

import java.nio.ByteBuffer;

/**
 *
 */
@Structure
public class PacketHeaderStructure extends EmptyHeader implements SelfParser<PacketHeaderStructure> {
    @Field
    private int seconds;
    @Field
    private int microSeconds;
    @Field
    private int includedLength;
    @Field
    private int actualLength;

    public int getSeconds() {
        return seconds;
    }

    public int getMicroSeconds() {
        return microSeconds;
    }

    public int getIncludedLength() {
        return includedLength;
    }

    public int getActualLength() {
        return actualLength;
    }

    @Override
    public String toString() {
        return "PacketHeaderStructure{" +
                "seconds=" + seconds +
                ", microSeconds=" + microSeconds +
                ", includedLength=" + includedLength +
                ", actualLength=" + actualLength +
                '}';
    }

    @Override
    public void parse(StructureParser<PacketHeaderStructure> parser, ByteBuffer buffer, int begin, int length) {
        this.seconds = buffer.getInt(begin);
        this.microSeconds = buffer.getInt(begin + 4);
        this.includedLength = buffer.getInt(begin + 8);
        this.actualLength = buffer.getInt(begin + 12);
    }
}
