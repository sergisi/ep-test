package data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class AbstractData<E, T extends DataInterface<E>> {

    T fstInstance, sndInstance, thdInstance;
    E fstValue, sndValue;


    @Test
    void testGetValue() {
        assertEquals(fstValue, fstInstance.getValue());
        assertEquals(fstValue, sndInstance.getValue());
        assertEquals(sndValue, thdInstance.getValue());
    }

    @Test
    void testEquals() {
        assertEquals(fstInstance, fstInstance);
        assertEquals(sndInstance, sndInstance);
        assertNotEquals(sndInstance, thdInstance);
    }

    @Test
    void testHashCode() {
        assertEquals(fstInstance.getValue().hashCode(), fstInstance.hashCode());
    }

}
