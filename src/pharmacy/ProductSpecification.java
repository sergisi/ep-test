package pharmacy;

import java.math.BigDecimal;
import data.ProductID;

public class ProductSpecification {
    private final ProductID upccode;
    private final String description;
    private BigDecimal price;

    public ProductSpecification(ProductID upccode, String description, BigDecimal price) {
        this.upccode = upccode;
        this.description = description;
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public ProductID getUpccode() {
        return upccode;
    }
}
