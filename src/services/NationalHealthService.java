package services;

import data.*;
import exceptions.services.ConnectException;
import exceptions.services.HealthCardException;
import exceptions.services.NotValidePrescriptionException;
import exceptions.data.ProductIDException;
import pharmacy.Dispensing;
import pharmacy.ProductSpecification;

import java.util.List;

public interface NationalHealthService {
    Dispensing getePrescrption(HealthCardID hcid) throws HealthCardException, NotValidePrescriptionException, ConnectException;
    PatientContr getPatientContr(HealthCardID hcID) throws ConnectException;
    ProductSpecification getProductSpecific(ProductID pID) throws ProductIDException, ConnectException;
    List<Dispensing> updateePrescription(HealthCardID hcID, Dispensing disp) throws ConnectException;

}
