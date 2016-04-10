package lab.cadl.lirui.ppcap.core.utils;

/**
 *
 */
public class Utils {
    public static RuntimeException wrap(Exception ex) {
        return new RuntimeException(ex);
    }
}
