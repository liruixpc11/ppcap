package lab.cadl.lirui.ppcap.core.io.structures;

import lab.cadl.lirui.ppcap.core.io.annotations.Field;
import lab.cadl.lirui.ppcap.core.io.annotations.Structure;

/**
 *
 */
@Structure
public class PcapHeaderStructure {
    @Field
    private int magicNumber;
    @Field
    private short versionMajor;
    @Field
    private short versionMinor;
    @Field
    private int zone;
    @Field
    private int reserved;
    @Field
    private int snapLength;
    @Field
    private int network;

    public int getMagicNumber() {
        return magicNumber;
    }

    public short getVersionMajor() {
        return versionMajor;
    }

    public short getVersionMinor() {
        return versionMinor;
    }

    public int getZone() {
        return zone;
    }

    public int getReserved() {
        return reserved;
    }

    public int getSnapLength() {
        return snapLength;
    }

    public int getNetwork() {
        return network;
    }
}
