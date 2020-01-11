package data;

import exceptions.NotAValidValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductIDTest extends AbstractData<String, ProductID> implements DataTest {

    @BeforeEach
    public void setUp() throws NotAValidValue {
        fstValue = "Prod1";
        sndValue = "Prod2";
        fstInstance = new ProductID(fstValue);
        sndInstance = new ProductID(fstValue);
        thdInstance = new ProductID(sndValue);
    }

    @Test
    public void constructorExceptions() {
        assertThrows(NullPointerException.class, () -> new ProductID(null));
        assertThrows(NotAValidValue.class, () -> new ProductID("tt"));
    }

    @Test
    public void testToString() {
        assertEquals("ProductID{product code='Prod1'}", fstInstance.toString());
    }

}