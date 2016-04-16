package lab.cadl.lirui.ppcap.core;

import lab.cadl.lirui.ppcap.core.io.file.ChannelPcapFileReader;
import lab.cadl.lirui.ppcap.core.io.file.PcapFileReader;
import lab.cadl.lirui.ppcap.core.io.file.StreamPcapFileReader;
import lab.cadl.lirui.ppcap.core.packet.Packet;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

/**
 *
 */
public class Test {
    private static final Path pcapFile = Paths.get("data/LLS_DDOS_2.0.2-inside.dump.pcap");
    private int a;

    public static void main(String[] args) throws Exception {
        testPcapFileReadUseStream(100);
//        testPcapFileRead(10);
//        testRawFileReadUseStream(100);
//        testRawFileRead(10);
    }

    public static long test(String name, Call call, int count) {
        long ms = 0;
        for (int i = 0; i < count; i++) {
            ms += test(name, call);
        }

        System.out.println("==AVERAGE " + ms / count + "ms");

        return ms;
    }

    public static long testRawFileReadUseStream(int count) {
        return test("read pcap raw by stream " + pcapFile, () -> {
            try (InputStream channel = new BufferedInputStream(new FileInputStream(pcapFile.toFile()))) {
                // file header
                if (channel.read(new byte[24]) < 24) {
                    throw new RuntimeException("header too short");
                }

                // packet header
                byte[] bytes = new byte[16];
                ByteBuffer buffer = ByteBuffer.wrap(bytes);
                while (channel.read(buffer.array()) == 16) {
                    int length = buffer.getInt(8);
                    buffer.clear();

                    ByteBuffer payloadBuffer = ByteBuffer.allocate(length);
                    if (channel.read(payloadBuffer.array()) < length) {
                        throw new RuntimeException("payload too short");
                    }
                    payloadBuffer.getInt();
                }
            }
        }, count);
    }

    public static long testRawFileRead(int count) {
        return test("read pcap raw " + pcapFile, () -> {
            try (FileChannel channel = FileChannel.open(pcapFile, EnumSet.of(StandardOpenOption.READ))) {
                // file header
                ByteBuffer buffer = ByteBuffer.allocate(24);
                channel.read(buffer);

                // packet header
                buffer = ByteBuffer.allocate(16);
                while (channel.read(buffer) == 16) {
                    buffer.flip();
                    int length = buffer.getInt(8);
                    buffer.clear();

                    ByteBuffer payloadBuffer = ByteBuffer.allocate(length);
                    channel.read(payloadBuffer);
                    payloadBuffer.flip();
                    payloadBuffer.getInt();
                }
            }
        }, count);
    }

    public static long testPcapFileRead(int count) {
        return test("read and parse pcap " + pcapFile, () -> {
            try (PcapFileReader reader = new ChannelPcapFileReader(pcapFile)) {
                while (reader.hasNext()) {
                    reader.next();
                }
            }
        }, count);
    }

    public static long testPcapFileReadUseStream(int count) {
        return test("read and parse pcap by stream " + pcapFile, () -> {
            try (PcapFileReader reader = new StreamPcapFileReader(pcapFile)) {
                while (reader.hasNext()) {
                    Packet packet = reader.next();
                    System.out.println(packet.payload().header());
                }
            }
        }, count);
    }

    @FunctionalInterface
    interface Call {
        void call() throws Exception;
    }

    private static long test(String name, Call callable) {
        System.out.println(">> BEGIN " + name);
        long begin = System.currentTimeMillis();
        long ms;
        try {
            callable.call();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ms = System.currentTimeMillis() - begin;
            System.out.println(">> END IN " + ms + "ms");
        }

        return ms;
    }

    private static void testReflection() throws Exception {
        Test test = new Test();
        Field field = test.getClass().getDeclaredField("a");

        test("reflection", () -> {
            for (int i = 0; i < 1000_000_000; i++) {
                field.set(test, 1);
            }
        });

        test("reflection int", () -> {
            for (int i = 0; i < 1000_000_000; i++) {
                field.setInt(test, 1);
            }
        });

        test("direct", () -> {
            for (int i = 0; i < 1000_000_000; i++) {
                test.a = 1;
            }
        });
    }
}
