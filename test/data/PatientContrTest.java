package data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class PatientContrTest {

    private PatientContr fstInstance, sndInstance, thdInstance;
    BigDecimal fstValue, sndValue;

    @BeforeEach
    void setUp() {
        fstValue = new BigDecimal("0.3");
        sndValue = new BigDecimal("0.96");
        fstInstance = new PatientContr(fstValue);
        sndInstance = new PatientContr(fstValue);
        thdInstance = new PatientContr(sndValue);
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