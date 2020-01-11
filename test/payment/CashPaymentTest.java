package payment;

import data.PatientContr;
import data.ProductID;
import exceptions.*;
import mocks.SalesHistoryMock;
import mocks.WarehouseMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pharmacy.Sale;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CashPaymentTest {


    WarehouseMock warehouse;
    SalesHistoryMock salesHistory;
    CashPayment cashPayment1, cashPayment2;

    @BeforeEach
    void setUp() throws NotAValidValue, SaleClosedException {
        BigDecimal cash = new BigDecimal("6.00");
        Sale sale = new Sale();
        warehouse = new WarehouseMock();
        salesHistory = new SalesHistoryMock();
        PatientContr contr = new PatientContr(new BigDecimal("40"));
        ProductID prod1 = new ProductID("Prod1");
        ProductID prod2 = new ProductID("Prod2");
        BigDecimal price1 = BigDecimal.TEN;
        BigDecimal price2 = BigDecimal.valueOf(15);
        sale.addLine(prod1, price1, contr);
        sale.addLine(prod2, price2, contr);
        sale.calculateFinalAmount();
        cashPayment1 = new CashPayment(cash, sale, warehouse, salesHistory);
        cashPayment2 = new CashPayment(new BigDecimal("13.000"), sale, warehouse, salesHistory);
    }

    @Test
    void payMethod() throws PaymentException, InsufficientExistences {
        assertThrows(QuantityMinorThanImport.class, () -> cashPayment1.payMethod());
        assertDoesNotThrow(() -> cashPayment2.payMethod());
        cashPayment2.pay();
        assertTrue(warehouse.isWascalled());
        assertTrue(salesHistory.isCalled());
    }

    @Test
    void change() throws PaymentException, InsufficientExistences {
        cashPayment2.pay();
        assertEquals(new BigDecimal("0.900"), cashPayment2.change());
    }
}