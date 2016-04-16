package lab.cadl.lirui.ppcap.core.packet;

import java.nio.ByteBuffer;

/**
 *
 */
public abstract class AbstractRawData implements RawData {
    private ByteBuffer rawData;
    private int begin;
    private int length;

    public AbstractRawData(ByteBuffer rawData, int begin, int length) {
        this.rawData = rawData;
        this.begin = begin;
        this.length = length;
    }

    public AbstractRawData(ByteBuffer rawData, int length) {
        this.rawData = rawData;
        this.begin = 0;
        this.length = length;
    }

    public AbstractRawData() {
    }

    public void setRawData(ByteBuffer rawData) {
        this.rawData = rawData;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public ByteBuffer rawData() {
        return rawData;
    }

    @Override
    public int begin() {
        return begin;
    }

    @Override
    public int length() {
        return length;
    }
}
