package pharmacy;

import data.ProductID;
import exceptions.*;
import mocks.HealthCardReaderMock;
import mocks.NationalHealthMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class DispensingTerminalTest {

    private DispensingTerminal dt;
    private NationalHealthMock snsmock;

    @BeforeEach
    void setUp() {
        snsmock = new NationalHealthMock();
        dt = new DispensingTerminal(new HealthCardReaderMock(), snsmock);
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
    void finalizeSale() throws NotAValidValue, DispensingNotAvailableException, MedicineAlreadyDispencedException, MedicineNotInDispensingListException, ConnectException, ProductIDException, SaleClosedException, NotValidePrescriptionException, HealthCardException, PatientIDException {
        dt.getePrescription('a');
        dt.initNewSale();
        dt.enterProduct(new ProductID("Prod1"));
        dt.enterProduct(new ProductID("Prod2"));
        dt.finalizeSale();
        assertEquals(new BigDecimal("12.100"), dt.getSale().getAmount());
        assertTrue(snsmock.wasCalledUpdate());
    }
}