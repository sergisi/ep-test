package data;

import exceptions.NotAValidValue;

import javax.xml.crypto.Data;

final public class ProductID implements DataInterface<String> {


    private final String productID;
    private static String regex = "(Prod1)|(Prod2)|(Prod3)";
    //TODO : Change regex so it makes sense. Note that tests will have to change accordingly.

    public ProductID(String code) throws NotAValidValue {
        if (code == null) {
            throw new NullPointerException("A value class must not be null.");
        }
        if (!code.matches(regex)) {
            throw new NotAValidValue("Code is not a product ID.");
        }
        this.productID = code;
    }

    public String getProductID() {
        return productID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductID prodID = (ProductID) o;
        return productID.equals(prodID.productID);
    }

    @Override
    public int hashCode() {
        return productID.hashCode();
    }

    @Override
    public String toString() {
        return "ProductID{" + "product code='" + productID + '\'' + '}';
    }

    @Override
    public String getValue() {
        return getProductID();
    }
}
