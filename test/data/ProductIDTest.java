package data;

import exceptions.NotAValidValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductIDTest {

    private ProductID fstInstance, sndInstance, thdInstance;
    String fstValue, sndValue;

    @BeforeEach
    void setUp() throws NotAValidValue {
        fstValue = "Prod1";
        sndValue = "Prod2";
        fstInstance = new ProductID(fstValue);
        sndInstance = new ProductID(fstValue);
        thdInstance = new ProductID(sndValue);
    }

    @Test
    void constructorExceptions() {
        assertThrows(NullPointerException.class, () -> new ProductID(null));
        assertThrows(NotAValidValue.class, () -> new ProductID("tt"));
    }

    @Test
    void getHealthCardId() {
        assertEquals(fstValue, fstInstance.getProductID());
        assertEquals(fstValue, sndInstance.getProductID());
        assertEquals(sndValue, thdInstance.getProductID());
    }

    @Test
    void testEquals() {
        assertEquals(fstInstance, fstInstance);
        assertEquals(sndInstance, sndInstance);
        assertNotEquals(sndInstance, thdInstance);
    }

    @Test
    void testHashCode() {
        assertEquals(fstInstance.getProductID().hashCode(), fstInstance.hashCode());
    }

    @Test
    void testToString() {
        assertEquals("ProductID{product code='Prod1'}", fstInstance.toString());
    }

}