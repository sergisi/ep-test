package data;

import exceptions.NotAValidValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HealthCardIDTest {

    private HealthCardID fstInstance, sndInstance, thdInstance;
    String fstValue, sndValue;

    @BeforeEach
    void setUp() throws NotAValidValue {
        fstValue = "Jo";
        sndValue = "Tu";
        fstInstance = new HealthCardID(fstValue);
        sndInstance = new HealthCardID(fstValue);
        thdInstance = new HealthCardID(sndValue);
    }

    @Test
    void constructorExceptions() {
        assertThrows(NullPointerException.class, () -> new HealthCardID(null));
        assertThrows(NotAValidValue.class, () -> new HealthCardID("tt"));
    }

    @Test
    void getHealthCardId() {
        assertEquals(fstValue, fstInstance.getPersonalID());
        assertEquals(fstValue, sndInstance.getPersonalID());
        assertEquals(sndValue, thdInstance.getPersonalID());
    }

    @Test
    void testEquals() {
        assertEquals(fstInstance, fstInstance);
        assertEquals(sndInstance, sndInstance);
        assertNotEquals(sndInstance, thdInstance);
    }

    @Test
    void testHashCode() {
        assertEquals(fstInstance.getPersonalID().hashCode(), fstInstance.hashCode());
    }

    @Test
    void testToString() {
        assertEquals("HealthCardID{personal code='Jo'}", fstInstance.toString());
    }

}