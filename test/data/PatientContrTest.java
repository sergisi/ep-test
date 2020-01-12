package data;

import exceptions.data.NotAValidValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PatientContrTest extends AbstractData<BigDecimal, PatientContr> implements DataTest{

    @BeforeEach
    public void setUp() throws NotAValidValue {
        fstValue = new BigDecimal("0.3");
        sndValue = new BigDecimal("0.96");
        fstInstance = new PatientContr(fstValue);
        sndInstance = new PatientContr(fstValue);
        thdInstance = new PatientContr(sndValue);
    }


    @Test
    public void constructorExceptions() {
        assertThrows(NullPointerException.class, () -> new PatientContr(null));
        assertThrows(NotAValidValue.class, () -> new PatientContr(BigDecimal.valueOf(-1)));
    }

    @Test
    public void testToString() {
        assertEquals("PatientContr{patient contribution='0.3'}", fstInstance.toString());
    }

}