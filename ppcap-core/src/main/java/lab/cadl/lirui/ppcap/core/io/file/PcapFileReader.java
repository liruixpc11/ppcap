package lab.cadl.lirui.ppcap.core.io.file;

import lab.cadl.lirui.ppcap.core.io.PacketReader;
import lab.cadl.lirui.ppcap.core.io.structures.PacketHeaderStructure;
import lab.cadl.lirui.ppcap.core.io.structures.PcapHeaderStructure;
import lab.cadl.lirui.ppcap.core.io.utils.StructureParser;
import lab.cadl.lirui.ppcap.core.io.utils.StructureParserFactory;
import lab.cadl.lirui.ppcap.core.packet.Packet;
import lab.cadl.lirui.ppcap.core.packet.RawPacket;
import lab.cadl.lirui.ppcap.core.utils.Utils;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

/**
 *
 */
public abstract class PcapFileReader implements PacketReader {
    private static final StructureParser<PcapHeaderStructure> headerParser = StructureParserFactory.getInstance().getParser(PcapHeaderStructure.class);
    private static final StructureParser<PacketHeaderStructure> packetHeaderParser = StructureParserFactory.getInstance().getParser(PacketHeaderStructure.class);
    private final long size;
    private long position;

    public PcapFileReader(Path path) {
        try {
            size = open(path);
            PcapHeaderStructure header = read(headerParser);
            position += headerParser.length();
        } catch (IOException e) {
            throw Utils.wrap(e);
        }
    }

    protected abstract long open(Path path) throws IOException;

    private <T> T read(StructureParser<T> parser) {
        return read(parser, null);
    }

    protected <T> T read(StructureParser<T> parser, T structure) {
        int length = parser.length();
        try {
            ByteBuffer buffer = ByteBuffer.allocate(length);
            if (read(buffer) < length) {
                throw new RuntimeException("file content is too short");
            }

            buffer.flip();
            if (structure != null) {
                return parser.parse(structure, buffer, 0, length);
            } else {
                return parser.parse(buffer, 0, length);
            }
        } catch (IOException e) {
            throw Utils.wrap(e);
        }
    }

    protected abstract long read(ByteBuffer buffer) throws IOException;

    @Override
    public boolean hasNext() {
        return position < size;
    }

    @Override
    public RawPacket next() {
        try {
            PacketHeaderStructure header = new PacketHeaderStructure();
            read(packetHeaderParser, header);
            int length = header.getIncludedLength();

            ByteBuffer buffer = ByteBuffer.allocate(length);
            if (read(buffer) < length) {
                throw new RuntimeException("payload too short");
            }

            buffer.flip();
            position += packetHeaderParser.length() + length;
            return new RawPacket(header, buffer);
        } catch (IOException e) {
            throw Utils.wrap(e);
        }

    }
}
