package services;

import data.*;
import exceptions.ConnectException;
import exceptions.HealthCardException;
import exceptions.NotValidePrescriptionException;
import exceptions.ProductIDException;
import pharmacy.Dispensing;
import pharmacy.ProductSpecification;

import java.util.List;

public interface NationalHealthService {
    Dispensing getePrescrption(HealthCardID hcid) throws HealthCardException, NotValidePrescriptionException, ConnectException;
    PatientContr getPatientContr(HealthCardID hcID) throws ConnectException;
    ProductSpecification getProductSpecific(ProductID pID) throws ProductIDException, ConnectException;
    List<Dispensing> updateePrescription(HealthCardID hcID, Dispensing disp) throws ConnectException;

}
