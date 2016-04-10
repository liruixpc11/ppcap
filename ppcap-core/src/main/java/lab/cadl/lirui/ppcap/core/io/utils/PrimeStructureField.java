package lab.cadl.lirui.ppcap.core.io.utils;

import lab.cadl.lirui.ppcap.core.utils.Utils;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;

/**
 *
 */
public class PrimeStructureField<T> extends AbstractStructureField<T> {
    private FieldSetter<T> setter;

    public PrimeStructureField(int begin, int length, FieldSetter<T> setter) {
        super(begin, length);
        this.setter = setter;
    }

    @Override
    public void parseTo(T structure, ByteBuffer buffer, int sBegin) {
        try {
            setter.set(structure, buffer, sBegin);
        } catch (Exception e) {
            throw Utils.wrap(e);
        }
    }
}
