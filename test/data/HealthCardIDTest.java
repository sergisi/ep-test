package data;

import exceptions.data.NotAValidValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HealthCardIDTest extends AbstractData<String, HealthCardID> implements DataTest{


    @BeforeEach
    public void setUp() throws NotAValidValue {
        fstValue = "Jo";
        sndValue = "Tu";
        fstInstance = new HealthCardID(fstValue);
        sndInstance = new HealthCardID(fstValue);
        thdInstance = new HealthCardID(sndValue);
    }

    @Test
    public void constructorExceptions() {
        assertThrows(NullPointerException.class, () -> new HealthCardID(null));
        assertThrows(NotAValidValue.class, () -> new HealthCardID("tt"));
    }

    @Test
    public void testToString() {
        assertEquals("HealthCardID{personal code='Jo'}", fstInstance.toString());
    }

}