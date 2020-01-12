package pharmacy;

import data.ProductID;
import exceptions.data.NotAValidValue;
import exceptions.pharmacy.DispensingNotAvailableException;
import exceptions.pharmacy.MedicineAlreadyDispencedException;
import exceptions.pharmacy.MedicineNotInDispensingListException;
import exceptions.services.NotAllMedicamentsDispensedException;
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
    private byte nOrder;
    Date initDate;
    Date finalDate;
    HashMap<ProductID, MedicineDispensingLine> medicines;
    boolean isCompleted;

    @BeforeEach
    void setup() throws ParseException, NotAValidValue {
        String initDayError = "2020/09/30 08:00:00";
        String initDay = "2020/01/01 08:00:00";
        String finalDay = "2020/10/30 20:00:00";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date initDateError = sdf.parse(initDayError);
        initDate = sdf.parse(initDay);
        finalDate = sdf.parse(finalDay);
        medicines = new HashMap<>();
        idProduct1 = new ProductID("Prod1");
        idProduct2 = new ProductID("Prod2");
        MedicineDispensingLine med1 = new MedicineDispensingLine(idProduct1);
        MedicineDispensingLine med2 = new MedicineDispensingLine(idProduct2);
        medicines.put(med1.getMed(), med1);
        medicines.put(med2.getMed(), med2);
        nOrder = 10;
        toDispenseError = new Dispensing(nOrder, initDateError, finalDate, medicines);
        toDispense = new Dispensing(nOrder, initDate, finalDate, medicines);

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

    @Test
    void getnOrder(){
        assertEquals(nOrder, toDispense.getnOrder());
    }

    @Test
    void setnOrder() {
        toDispense.setnOrder((byte) 9);
        assertEquals((byte) 9, toDispense.getnOrder());
    }

    @Test
    void getInitDate(){
        assertEquals(initDate, toDispense.getInitDate());
    }

    @Test
    void setInitDate() throws ParseException {
        String initDay = "2020/01/05 08:00:00";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date initDateError = sdf.parse(initDay);
        toDispense.setInitDate(initDateError);
        assertEquals(initDateError, toDispense.getInitDate());
    }

    @Test
    void getFinalDate(){
        assertEquals(finalDate, toDispense.getFinalDate());
    }

    @Test
    void setFinalDate() throws ParseException {
        String finalDay = "2020/01/015 08:00:00";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date finalDate = sdf.parse(finalDay);
        toDispense.setFinalDate(finalDate);
        assertEquals(finalDate, toDispense.getFinalDate());
    }

    @Test
    void getMedicines(){
        assertEquals(medicines, toDispense.getMedicines());
    }

    @Test
    void setMedicines(){
        HashMap<ProductID, MedicineDispensingLine> med = new HashMap<>();
        MedicineDispensingLine med1 = new MedicineDispensingLine(idProduct1);
        MedicineDispensingLine med2 = new MedicineDispensingLine(idProduct2);
        med.put(med1.getMed(), med1);
        med.put(med2.getMed(), med2);
        toDispense.setMedicines(med);
        assertEquals(med, toDispense.getMedicines());
    }
}