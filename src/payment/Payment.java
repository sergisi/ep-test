package payment;

import exceptions.services.InsufficientExistences;
import exceptions.payment.PaymentException;
import pharmacy.Sale;
import services.SalesHistory;
import services.Warehouse;

public abstract class Payment {

    Warehouse warehouse;
    SalesHistory salesHistory;
    Sale sale;
    abstract void payMethod() throws PaymentException;

    public void pay() throws InsufficientExistences, PaymentException {
        payMethod();
        warehouse.updateStock(sale.getProductsSold());
        salesHistory.registerSale(sale);
    }
}
