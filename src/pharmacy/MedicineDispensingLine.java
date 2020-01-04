package pharmacy;

import data.ProductID;

import java.math.BigDecimal;

public class MedicineDispensingLine{

    private ProductID med;
    private boolean acquired;


    public MedicineDispensingLine(ProductID med){
        this.acquired=false;
        this.med=med;
    }

    public boolean getAcquired(){
        return this.acquired;
    }

    public void setAcquired(boolean acquired){
        this.acquired=acquired;
    }

    public ProductID getMed(){
        return this.med;
    }

    public void setMed(ProductID acquired){
        this.med=med;
    }

}
