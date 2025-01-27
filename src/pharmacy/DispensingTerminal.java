package pharmacy;

import data.HealthCardID;
import data.PatientContr;
import data.ProductID;
import exceptions.data.PatientIDException;
import exceptions.data.ProductIDException;
import exceptions.payment.PaymentException;
import exceptions.pharmacy.*;
import exceptions.services.*;
import payment.CashPayment;
import payment.Payment;
import services.HealthCardReader;
import services.NationalHealthService;
import services.SalesHistory;
import services.Warehouse;

import java.math.BigDecimal;

public class DispensingTerminal {

    private HealthCardReader healthCardReader;
    private NationalHealthService nationalHealthService;
    private Dispensing activeePrescription;
    private Sale sale;
    private PatientContr contr;
    private HealthCardID hid;
    private Warehouse warehouse;
    private SalesHistory salesHistory;
    private Payment payment;

    public DispensingTerminal(HealthCardReader healthReader, NationalHealthService sns,
                              Warehouse warehouse, SalesHistory salesHistory) {
        healthCardReader = healthReader;
        nationalHealthService = sns;
        this.warehouse = warehouse;
        this.salesHistory = salesHistory;
    }

    public void getePrescription(char option) throws ConnectException, NotValidePrescriptionException,
            PatientIDException, HealthCardException {
        hid = healthCardReader.getHealthCardID();
        activeePrescription = nationalHealthService.getePrescrption(hid);
        contr = nationalHealthService.getPatientContr(hid);
    }

    public void initNewSale() throws DispensingNotAvailableException {
        if (activeePrescription == null) throw new DispensingNotAvailableException();
        // TODO : understand wh sale and dispensing need to be connected.
        sale = new Sale();
    }

    public void enterProduct(ProductID pID) throws ProductIDException, ConnectException, SaleClosedException,
            MedicineAlreadyDispencedException, MedicineNotInDispensingListException {
        ProductSpecification productSpecification = nationalHealthService.getProductSpecific(pID);
        sale.addLine(pID, productSpecification.getPrice(), contr);
        activeePrescription.setProductAsDispensed(pID);
    }

    public void finalizeSale() throws SaleClosedException, ConnectException {
        sale.calculateFinalAmount();
    }

    public void realizePayment(BigDecimal quantity) throws ConnectException, PaymentException,
            SaleNotClosedException, BrokenServicesException, InsufficientExistences {
        if (!sale.isClosed()) throw new SaleNotClosedException();
        payment = new CashPayment(quantity, sale, warehouse, salesHistory);
        payment.pay();
        nationalHealthService.updateePrescription(hid, activeePrescription);
    }

    public Payment getPayment() {
        return payment;
    }

    public void realizePayment() {
        // Pay with card: not to be implemented
    }

    public void printNextDispensingSheet() {
        // Does not need to be implemented
        // If this was an a real project the next to do would not be done in
        // the next 5 years
        // TODO : do
    }

    public Dispensing getActiveePrescription() {
        return activeePrescription;
    }

    public Sale getSale() {
        return sale;
    }


}
