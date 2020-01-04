package pharmacy;

import data.ProductID;
import exceptions.NotAValidValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductSaleLineTest {

    private ProductSaleLine prodsl;
    private ProductID product;
    @BeforeEach
    void setUp() throws NotAValidValue {
        product = new ProductID("Prod1");
        prodsl = new ProductSaleLine(BigDecimal.TEN, product);
    }

    @Test
    void getSubtotal() {
        assertEquals(BigDecimal.TEN, prodsl.getSubtotal());
    }

    @Test
    void setSubtotal() {
        prodsl.setSubtotal(BigDecimal.ZERO);
        assertEquals(BigDecimal.ZERO, prodsl.getSubtotal());
    }

    @Test
    void getProd() {
        assertEquals(product, prodsl.getProd());
    }

    @Test
    void testEquals() throws NotAValidValue {
        ProductSaleLine theOtherProductSaleLine = new ProductSaleLine(BigDecimal.TEN, new ProductID("Prod1"));
        assertEquals(theOtherProductSaleLine, prodsl);
    }
}