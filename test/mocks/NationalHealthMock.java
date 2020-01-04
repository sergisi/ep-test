package mocks;

import data.HealthCardID;
import data.PatientContr;
import data.ProductID;
import exceptions.*;
import pharmacy.Dispensing;
import pharmacy.ProductSpecification;
import services.NationalHealthService;

import java.math.BigDecimal;
import java.util.List;

public class NationalHealthMock implements NationalHealthService {

    @Override
    public Dispensing getePrescrption(HealthCardID hcid) throws HealthCardException, NotValidePrescriptionException, ConnectException {
        return null;  // TODO @sergisi change later according to the cherrypick.
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
        if ("Prod1".equals(pID.getProductID())) {
            return new ProductSpecification(pID, "Iburprofeno", BigDecimal.TEN);
        } else {
            return new ProductSpecification(pID, "Paracetamol", BigDecimal.valueOf(15));
        }
    }

    @Override
    public List<Dispensing> updateePrescription(HealthCardID hcID, Dispensing disp) throws ConnectException {
        return null;  // TODO @sergisi mock this.
    }
}
