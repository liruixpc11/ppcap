package lab.cadl.lirui.ppcap.core.io.utils;

import lab.cadl.lirui.ppcap.core.io.annotations.Structure;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 */
public final class StructureParserFactory {
    private static final Map<Class<?>, PrimeEntry> primeClasses = new Builder()
            .add(Long.class, 8, ByteBuffer::getLong)
            .add(long.class, 8, ByteBuffer::getLong)
            .add(Integer.class, 4, ByteBuffer::getInt)
            .add(int.class, 4, ByteBuffer::getInt)
            .add(Short.class, 2, ByteBuffer::getShort)
            .add(short.class, 2, ByteBuffer::getShort)
            .add(Character.class, 2, ByteBuffer::getChar)
            .add(char.class, 2, ByteBuffer::getChar)
            .add(Byte.class, 1, ByteBuffer::get)
            .add(byte.class, 1, ByteBuffer::get)
            .add(Float.class, 4, ByteBuffer::getFloat)
            .add(float.class, 4, ByteBuffer::getFloat)
            .add(Double.class, 8, ByteBuffer::getDouble)
            .add(double.class, 8, ByteBuffer::getDouble)
            .build();

    private static final StructureParserFactory instance = new StructureParserFactory();

    private final ConcurrentHashMap<Class<?>, StructureParser<?>> parserMap = new ConcurrentHashMap<>();

    public static StructureParserFactory getInstance() {
        return instance;
    }

    private StructureParserFactory() {}

    @SuppressWarnings("unchecked")
    public <T> StructureParser<T> getParser(Class<T> tClass) {
        StructureParser<T> parser = (StructureParser<T>) parserMap.get(tClass);
        if (parser == null) {
            parser = createParser(tClass);
            parserMap.put(tClass, parser);
        }

        return parser;
    }

    public <T> StructureParser<T> createParser(Class<T> tClass) {
        if (!tClass.isAnnotationPresent(Structure.class)) {
            throw new IllegalArgumentException("class " + String.valueOf(tClass) + " without " + Structure.class.getSimpleName() + " annotation");
        }

        StructureParser<T> parser = new StructureParser<>(tClass);
        for (Field field : tClass.getDeclaredFields()) {
            lab.cadl.lirui.ppcap.core.io.annotations.Field fieldAnnotation = field.getAnnotation(lab.cadl.lirui.ppcap.core.io.annotations.Field.class);
            field.setAccessible(true);
            parser.field(parseField(parser.length(), field, fieldAnnotation));
        }

        return parser;
    }

    private <T> StructureField<T> parseField(int currentIndex, Field field, lab.cadl.lirui.ppcap.core.io.annotations.Field fieldAnnotation) {
        int begin = adjust(fieldAnnotation.begin(), currentIndex);
        if (isPrimeClass(field.getType())) {
            PrimeEntry entry = primeClasses.get(field.getType());
            int length = adjust(fieldAnnotation.length(), entry.defaultLength);
            return new PrimeStructureField<>(begin, length, (structure, buffer, base) -> field.set(structure, entry.reader.read(buffer, base + begin)));
        } else {
            StructureParser parser = getParser(field.getType());
            int length = adjust(fieldAnnotation.length(), parser.length());
            return new ObjectStructureField<>(begin, length, field, parser);
        }
    }

    private boolean isPrimeClass(Class<?> type) {
        return primeClasses.containsKey(type);
    }

    private int adjust(int value, int defaultValue) {
        if (value < 0) {
            return defaultValue;
        }

        return value;
    }

    @FunctionalInterface
    private interface Reader {
        Object read(ByteBuffer buffer, int base);
    }

    private static class Builder {
        Map<Class<?>, PrimeEntry> map = new HashMap<>();

        Builder add(Class<?> t, int defaultLength, Reader reader) {
            map.put(t, new PrimeEntry(defaultLength, reader));
            return this;
        }

        Map<Class<?>, PrimeEntry> build() {
            return map;
        }
    }

    private static class PrimeEntry {
        private int defaultLength;
        private Reader reader;

        public PrimeEntry(int defaultLength, Reader reader) {
            this.defaultLength = defaultLength;
            this.reader = reader;
        }
    }
}
