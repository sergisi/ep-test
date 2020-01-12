package exceptions.payment;

import exceptions.payment.PaymentException;

public class QuantityMinorThanImport extends PaymentException {
    public QuantityMinorThanImport(String s) {
        super(s);
    }
}
