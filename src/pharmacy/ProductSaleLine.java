package pharmacy;

import data.ProductID;

import java.math.BigDecimal;

public class ProductSaleLine {
    private BigDecimal subtotal;
    private ProductID prod;

    // TODO: WHAT?
    // TODO: maybe subtotal is final. Maybe I don't know what to do with this class.
    public ProductSaleLine(BigDecimal subtotal, ProductID prod) {
        this.subtotal = subtotal;
        this.prod = prod;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public ProductID getProd() {
        return prod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductSaleLine prodsl = (ProductSaleLine) o;
        boolean b = subtotal.equals(prodsl.subtotal);
        boolean c = prod.equals(prodsl.prod);
        return b && c;
    }

}
