package pharmacy;

import data.PatientContr;
import data.ProductID;
import exceptions.NotAValidValue;
import exceptions.SaleClosedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SaleTest {

    private Sale sale;
    private PatientContr contr;
    private ProductID prod1, prod2;
    private BigDecimal price1, price2;

    @BeforeEach
    void setup() throws NotAValidValue {
        sale = new Sale();
        contr = new PatientContr(new BigDecimal("40"));
        prod1 = new ProductID("Prod1");
        prod2 = new ProductID("Prod2");
        price1 = BigDecimal.TEN;
        price2 = BigDecimal.valueOf(15);
    }


    @Test
    void addLine() throws SaleClosedException {
        LinkedList<ProductSaleLine> expected = new LinkedList<>();
        expected.add(new ProductSaleLine(new BigDecimal("4.0"), prod1));
        sale.addLine(prod1, price1, contr);
        assertEquals(expected, sale.getProductsSold());
        expected.add(new ProductSaleLine(new BigDecimal("6.0"), prod2));
        sale.addLine(prod2, price2, contr);
        assertEquals(expected, sale.getProductsSold());
    }

    @Test
    void allSaleClosedException() throws SaleClosedException {
        sale.calculateFinalAmount();
        assertTrue(sale.isClosed());
        assertThrows(SaleClosedException.class, () -> sale.calculateFinalAmount());
        assertThrows(SaleClosedException.class, () -> sale.addLine(prod1, price1, contr));
    }

    @Test
    void calculateFinalAmount() throws SaleClosedException {
        sale.addLine(prod1, price1, contr);
        sale.addLine(prod2, price2, contr);
        sale.calculateFinalAmount();
        assertEquals(new BigDecimal("12.100"), sale.getAmount());
    }
}