package pharmacy;

import data.ProductID;
import exceptions.data.NotAValidValue;
import exceptions.data.PatientIDException;
import exceptions.data.ProductIDException;
import exceptions.payment.PaymentException;
import exceptions.payment.QuantityMinorThanImport;
import exceptions.pharmacy.*;
import exceptions.services.*;
import mocks.HealthCardReaderMock;
import mocks.NationalHealthMock;
import mocks.SalesHistoryMock;
import mocks.WarehouseMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import payment.CashPayment;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class DispensingTerminalTest {

    private DispensingTerminal dt;
    private NationalHealthMock snsmock;
    private WarehouseMock warehouseMock;
    private SalesHistoryMock salesHistoryMock;

    @BeforeEach
    void setUp() {
        snsmock = new NationalHealthMock();
        warehouseMock = new WarehouseMock();
        salesHistoryMock = new SalesHistoryMock();
        dt = new DispensingTerminal(new HealthCardReaderMock(), snsmock, warehouseMock, salesHistoryMock);
    }

    @Test
    void getePrescription() throws HealthCardException, PatientIDException, ConnectException, NotValidePrescriptionException {
        dt.getePrescription('a');
        Dispensing disp = new NationalHealthMock().getePrescrption(new HealthCardReaderMock().getHealthCardID());
        Dispensing actual = dt.getActiveePrescription();
        boolean res = actual.equals(disp);
        assertEquals(disp, actual);
    }

    @Test
    void initNewSale() {
        assertThrows(DispensingNotAvailableException.class, () -> dt.initNewSale());
    }

    @Test
    void enterProduct() throws HealthCardException, PatientIDException, ConnectException,
            NotValidePrescriptionException, DispensingNotAvailableException, NotAValidValue,
            MedicineAlreadyDispencedException, MedicineNotInDispensingListException,
            ProductIDException, SaleClosedException {
        dt.getePrescription('a');
        dt.initNewSale();
        dt.enterProduct(new ProductID("Prod1"));
        assertEquals(1, dt.getSale().getProductsSold().size());
        assertTrue(dt.getActiveePrescription().getMedicines().get(new ProductID("Prod1")).getAcquired());
        assertFalse(dt.getActiveePrescription().getMedicines().get(new ProductID("Prod2")).getAcquired());
    }

    @Test
    void finalizeSale() throws NotAValidValue, DispensingNotAvailableException, MedicineAlreadyDispencedException,
            MedicineNotInDispensingListException, ConnectException, ProductIDException, SaleClosedException,
            NotValidePrescriptionException, HealthCardException, PatientIDException {
        dt.getePrescription('a');
        dt.initNewSale();
        dt.enterProduct(new ProductID("Prod1"));
        dt.enterProduct(new ProductID("Prod2"));
        dt.finalizeSale();

        assertEquals(new BigDecimal("12.100"), dt.getSale().getAmount());
    }

    @Test
    void realizePayment() throws PaymentException, SaleNotClosedException, ConnectException,
            InsufficientExistences, BrokenServicesException, NotValidePrescriptionException, HealthCardException,
            PatientIDException, DispensingNotAvailableException, NotAValidValue, MedicineAlreadyDispencedException,
            MedicineNotInDispensingListException, ProductIDException, SaleClosedException {
        dt.getePrescription('a');
        dt.initNewSale();
        dt.enterProduct(new ProductID("Prod1"));
        assertThrows(SaleNotClosedException.class, () -> dt.realizePayment(BigDecimal.TEN));
        dt.enterProduct(new ProductID("Prod2"));
        dt.finalizeSale();
        assertThrows(QuantityMinorThanImport.class, () -> dt.realizePayment(BigDecimal.valueOf(6)));
        dt.realizePayment(new BigDecimal("13.000"));
        CashPayment cashPayment = (CashPayment) dt.getPayment();
        assertEquals(new BigDecimal("0.900"), cashPayment.change());
        assertTrue(warehouseMock.isWascalled());
        assertTrue(salesHistoryMock.isCalled());
        assertTrue(snsmock.wasCalledUpdate());
    }
}