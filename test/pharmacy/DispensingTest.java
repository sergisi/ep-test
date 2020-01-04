package pharmacy;

import data.ProductID;
import exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class DispensingTest {

    private Dispensing toDispenseError;
    private Dispensing toDispense;
    private ProductID idProduct1;
    private ProductID idProduct2;

    @BeforeEach
    void setup() throws ParseException, NotAValidValue {
        String initDayError = "2020/09/30 08:00:00";
        String initDay = "2020/01/01 08:00:00";
        String finalDay = "2020/10/30 20:00:00";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date initDateError = sdf.parse(initDayError);
        Date initDate = sdf.parse(initDay);
        Date finalDate = sdf.parse(finalDay);
        HashMap<ProductID, MedicineDispensingLine> medicines = new HashMap<>();
        idProduct1 = new ProductID("Prod1");
        idProduct2 = new ProductID("Prod2");
        MedicineDispensingLine med1 = new MedicineDispensingLine(idProduct1);
        MedicineDispensingLine med2 = new MedicineDispensingLine(idProduct2);
        medicines.put(med1.getMed(), med1);
        medicines.put(med2.getMed(), med2);
        toDispenseError = new Dispensing((byte) 10, initDateError, finalDate, medicines);
        toDispense = new Dispensing((byte) 10, initDate, finalDate, medicines);

    }

    @Test
    void dispensingEnabled() throws DispensingNotAvailableException {
        assertThrows(DispensingNotAvailableException.class, () -> toDispenseError.dispensingEnabled());
        assertTrue(toDispense.dispensingEnabled());
    }

    @Test
    void setProductAsDispensed() throws MedicineNotInDispensingListException, MedicineAlreadyDispencedException, NotAValidValue {
        ProductID productIDError = new ProductID("Prod3");
        assertThrows(MedicineNotInDispensingListException.class, () -> toDispense.setProductAsDispensed(productIDError));
        toDispense.getMedicines().get(idProduct1).setAcquired(true);
        assertThrows(MedicineAlreadyDispencedException.class, () -> toDispense.setProductAsDispensed(idProduct1));
        toDispense.setProductAsDispensed(idProduct2);
        assertTrue(toDispense.getMedicines().get(idProduct2).getAcquired());

    }

    @Test
    void setComplited() throws NotAllMedicamentsDispensedException {
        assertThrows(NotAllMedicamentsDispensedException.class, () -> toDispense.setCompleated());
        toDispense.getMedicines().get(idProduct1).setAcquired(true);
        toDispense.getMedicines().get(idProduct2).setAcquired(true);
        toDispense.setCompleated();
        assertTrue(toDispense.getCompleted());
    }

}