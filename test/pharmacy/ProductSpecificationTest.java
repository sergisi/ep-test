package pharmacy;

import data.ProductID;
import exceptions.EmptyDescriptionException;
import exceptions.NotAValidValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductSpecificationTest {

    private ProductSpecification prodSpec;
    private ProductID product;

    @BeforeEach
    void setUp() throws NotAValidValue, EmptyDescriptionException {
        product = new ProductID("Prod1");
        prodSpec = new ProductSpecification(product, "description", BigDecimal.TEN);
    }


    @Test
    void createProductWithNoDescription(){
        assertThrows(EmptyDescriptionException.class, () -> new ProductSpecification(product,"", BigDecimal.TEN));
    }

    @Test
    void createProductIdNull(){
        assertThrows(NullPointerException.class, () -> new ProductSpecification(null, "description", BigDecimal.TEN));
    }

    @Test
    void createProductPriceNull(){
        assertThrows(NullPointerException.class, () -> new ProductSpecification(product,"description", null));
    }

    @Test
    void productDoesNotMatchRegex(){
        assertThrows(NotAValidValue.class, () -> new ProductSpecification(new ProductID("not-Valid-Code"),"description", BigDecimal.ONE));
    }

    @Test
    void setPriceNull(){
        assertThrows(NullPointerException.class, () -> prodSpec.setPrice(null));
    }

    @Test
    void getPrice() {
        assertEquals(BigDecimal.TEN, prodSpec.getPrice());
    }

    @Test
    void setPrice() {
        prodSpec.setPrice(BigDecimal.ONE);
        assertEquals(BigDecimal.ONE, prodSpec.getPrice());
    }

    @Test
    void getDescription() {
        assertEquals(prodSpec.getDescription(), "description");
    }

    @Test
    void getUpccode() {
        assertEquals(product, prodSpec.getUpccode());
    }
}