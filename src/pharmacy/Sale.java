package pharmacy;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import data.*;
import exceptions.pharmacy.SaleClosedException;

public class Sale {
    private static int currentSaleCode = 0;
    private int saleCode;
    private Date date;
    private BigDecimal amount;

    public List<ProductSaleLine> getProductsSold() {
        return productsSold;
    }

    private List<ProductSaleLine> productsSold;
    private boolean isClosed;
    private static BigDecimal iva = BigDecimal.valueOf(1.21);

    /**
     * Assigns the current date, a code to the sale, etc.
     */
    public Sale() {
        saleCode = currentSaleCode;
        currentSaleCode++;
        date = new Date();
        productsSold = new LinkedList<>();
        amount = BigDecimal.ZERO;
    }

    public void addLine(ProductID prodID, BigDecimal price, PatientContr contr) throws SaleClosedException {
        //TODO : Check which rounding mode should be used.
        if (isClosed) throw new SaleClosedException();
        BigDecimal subtotal = price.multiply(contr.getPatientContr().divide(BigDecimal.valueOf(100)));
        productsSold.add(new ProductSaleLine(subtotal, prodID));
    }

    private void calculateAmount() {
        Optional<BigDecimal> calculatedAmount = productsSold.stream()
                .map(ProductSaleLine::getSubtotal)
                .reduce(BigDecimal::add);
        amount = calculatedAmount.isEmpty() ? BigDecimal.ZERO : calculatedAmount.get();
    }

    private void addTaxes() throws SaleClosedException {
        if (isClosed) throw new SaleClosedException();
        amount = amount.multiply(iva);
    }

    public void calculateFinalAmount() throws SaleClosedException {
        calculateAmount();
        addTaxes();
        setClosed();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    private void setClosed() {
        isClosed = true;
    }

    public boolean isClosed() {
        return isClosed;
    }

    //TODO: ??? rest of getters setters and methods (in docs)
}

