package lab.cadl.lirui.ppcap.core.io.file;

import lab.cadl.lirui.ppcap.core.io.utils.StructureParser;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 *
 */
public class StreamPcapFileReader extends PcapFileReader {
    private InputStream inputStream;

    public StreamPcapFileReader(Path path) {
        super(path);
    }

    @Override
    protected long open(Path path) throws IOException {
        long size = Files.size(path);
        inputStream = new BufferedInputStream(new FileInputStream(path.toFile()));
        return size;
    }

    @Override
    protected long read(ByteBuffer buffer) throws IOException {
        int size = inputStream.read(buffer.array());
        buffer.position(buffer.position() + size);
        return size;
    }

    @Override
    public void close() {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException ignored) {
            }

            inputStream = null;
        }
    }
}
