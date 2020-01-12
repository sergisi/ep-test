package payment;

import exceptions.payment.QuantityMinorThanImport;
import pharmacy.Sale;
import services.SalesHistory;
import services.Warehouse;

import java.math.BigDecimal;

public class CashPayment extends Payment {

    BigDecimal quantity;

    public CashPayment(BigDecimal quantity, Sale sale, Warehouse wh, SalesHistory sh) {
        this.sale = sale;
        warehouse = wh;
        salesHistory = sh;
        this.quantity = quantity;
    }

    @Override
    void payMethod() throws QuantityMinorThanImport {
        if (quantity.compareTo(sale.getAmount()) < 0) {
            throw new QuantityMinorThanImport("Not enough cash");
        }
    }

    public BigDecimal change() throws QuantityMinorThanImport {
        if (quantity.compareTo(sale.getAmount()) < 0)
            throw new QuantityMinorThanImport("Not enough cash");
        return quantity.subtract(sale.getAmount());
    }
}
