package lab.cadl.lirui.ppcap.core;

import java.lang.reflect.Field;

/**
 *
 */
public class Test {
    private int a;

    public static void main(String[] args) throws Exception {
        Test test = new Test();
        Field field = test.getClass().getDeclaredField("a");

        System.out.println("reflection begin");
        long begin = System.currentTimeMillis();
        for (int i = 0; i < 1000_000_000; i++) {
            field.set(test, 1);
        }

        System.out.println(((System.currentTimeMillis() - begin) ) + "ms");

        System.out.println("reflection int begin");
        begin = System.currentTimeMillis();
        for (int i = 0; i < 1000_000_000; i++) {
            field.setInt(test, 1);
        }

        System.out.println(((System.currentTimeMillis() - begin) ) + "ms");

        System.out.println("direct begin");
        begin = System.currentTimeMillis();
        for (int i = 0; i < 1000_000_000; i++) {
            test.a = 1;
        }

        System.out.println(((System.currentTimeMillis() - begin) ) + "ms");
    }
}
