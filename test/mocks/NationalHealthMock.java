package mocks;

import data.HealthCardID;
import data.PatientContr;
import data.ProductID;
import exceptions.data.NotAValidValue;
import exceptions.data.ProductIDException;
import exceptions.pharmacy.EmptyDescriptionException;
import exceptions.services.ConnectException;
import exceptions.services.HealthCardException;
import exceptions.services.NotValidePrescriptionException;
import pharmacy.Dispensing;
import pharmacy.MedicineDispensingLine;
import pharmacy.ProductSpecification;
import services.NationalHealthService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class NationalHealthMock implements NationalHealthService {

    private boolean wasCalled;

    public NationalHealthMock() {
        wasCalled = false;
    }

    @Override
    public Dispensing getePrescrption(HealthCardID hcid) throws HealthCardException, NotValidePrescriptionException, ConnectException {
        Date first = new Date(1577840400000L);
        Date finalDate = new Date(1580518800000L);
        HashMap<ProductID, MedicineDispensingLine> medicines = new HashMap<>();
        try {
            ProductID idProduct1 = new ProductID("Prod1");
            ProductID idProduct2 = new ProductID("Prod2");
            MedicineDispensingLine med1 = new MedicineDispensingLine(idProduct1);
            MedicineDispensingLine med2 = new MedicineDispensingLine(idProduct2);
            medicines.put(med1.getMed(), med1);
            medicines.put(med2.getMed(), med2);
            return new Dispensing((byte) 0, first, finalDate, medicines);
        } catch (NotAValidValue notAValidValue) {
            throw new ConnectException();
        }
    }

    @Override
    public PatientContr getPatientContr(HealthCardID hcID) throws ConnectException {
        try {
            return new PatientContr(new BigDecimal("40"));
        } catch (NotAValidValue notAValidValue) {
            throw new ConnectException();
        }
    }

    @Override
    public ProductSpecification getProductSpecific(ProductID pID) throws ProductIDException, ConnectException {
        try {
            if ("Prod1".equals(pID.getProductID())) {
                return new ProductSpecification(pID, "Iburprofeno", BigDecimal.TEN);
            } else {
                return new ProductSpecification(pID, "Paracetamol", BigDecimal.valueOf(15));
            }
        } catch (EmptyDescriptionException e) {
            e.printStackTrace();
            throw new ProductIDException();
        }
    }

    @Override
    public List<Dispensing> updateePrescription(HealthCardID hcID, Dispensing disp) throws ConnectException {
        List<Dispensing> list = new LinkedList<>();
        list.add(disp);
        wasCalled = true;
        return list;
    }


    public boolean wasCalledUpdate() {
        return wasCalled;
    }
}
