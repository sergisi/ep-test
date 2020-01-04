package pharmacy;

import java.math.BigDecimal;
import data.ProductID;
import exceptions.EmptyDescriptionException;

public class ProductSpecification {
    private final ProductID upccode;
    private final String description;
    private BigDecimal price;


    public ProductSpecification(ProductID upccode, String description, BigDecimal price) throws EmptyDescriptionException{
        if(upccode == null) throw new NullPointerException("Product id can't be null");
        if(description.equals("")) throw new EmptyDescriptionException();
        if(price == null) throw new NullPointerException("Price can not be null");

        this.upccode = upccode;
        this.description = description;
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) throws NullPointerException{
        if(price == null) throw new NullPointerException("Price can't be null");
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public ProductID getUpccode() {
        return upccode;
    }
}
