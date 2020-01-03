package pharmacy;

import java.math.BigDecimal;
import java.util.Date;
import data.*;
import exceptions.SaleClosedException;

public class Sale {
    private int saleCode;
    private Date date;
    private BigDecimal amount;

    private boolean isClosed;

    /**
     * Assigns the current date, a code to the sale, etc.
     */
    public Sale() {

    }

    public void addLine(ProductID prodID, BigDecimal price, PatientContr contr) throws SaleClosedException {
    }

    public void calculateAmount() {
    }

    private void addTaxes() throws  SaleClosedException {

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

