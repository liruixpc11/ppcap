package lab.cadl.lirui.ppcap.core.io.file;

import lab.cadl.lirui.ppcap.core.io.utils.StructureParser;
import lab.cadl.lirui.ppcap.core.utils.Utils;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

/**
 *
 */
public class ChannelPcapFileReader extends PcapFileReader {
    private FileChannel channel;

    public ChannelPcapFileReader(Path path) {
        super(path);
    }

    @Override
    protected long open(Path path) throws IOException {
        channel = FileChannel.open(path, EnumSet.of(StandardOpenOption.READ));
        return channel.size();
    }

    @Override
    protected long read(ByteBuffer buffer) throws IOException {
        return channel.read(buffer);
    }

    @Override
    public void close() {
        if (channel != null) {
            try {
                channel.close();
            } catch (IOException ignored) {
            }

            channel = null;
        }
    }
}
