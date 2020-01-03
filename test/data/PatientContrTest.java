package data;

import exceptions.NotAValidValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PatientContrTest {

    private PatientContr fstInstance, sndInstance, thdInstance;
    BigDecimal fstValue, sndValue;

    @BeforeEach
    void setUp() throws NotAValidValue {
        fstValue = new BigDecimal("0.3");
        sndValue = new BigDecimal("0.96");
        fstInstance = new PatientContr(fstValue);
        sndInstance = new PatientContr(fstValue);
        thdInstance = new PatientContr(sndValue);
    }


    @Test
    void constructorExceptions() {
        assertThrows(NullPointerException.class, () -> new PatientContr(null));
        assertThrows(NotAValidValue.class, () -> new PatientContr(BigDecimal.valueOf(-1)));
    }

    @Test
    void getPatientContr() {
        assertEquals(fstValue, fstInstance.getPatientContr());
        assertEquals(fstValue, sndInstance.getPatientContr());
        assertEquals(sndValue, thdInstance.getPatientContr());
    }

    @Test
    void testEquals() {
        assertEquals(fstInstance, fstInstance);
        assertEquals(sndInstance, sndInstance);
        assertNotEquals(sndInstance, thdInstance);
    }

    @Test
    void testHashCode() {
        assertEquals(fstInstance.getPatientContr().hashCode(), fstInstance.hashCode());
    }

    @Test
    void testToString() {
        assertEquals("PatientContr{patient contribution='0.3'}", fstInstance.toString());
    }

}