package lab.cadl.lirui.ppcap.core.io.structures;

import lab.cadl.lirui.ppcap.core.io.annotations.Field;
import lab.cadl.lirui.ppcap.core.io.annotations.Structure;

/**
 *
 */
@Structure
public class PacketHeaderStructure {
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
}
