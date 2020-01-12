package pharmacy;

import data.ProductID;

import java.util.Objects;

public class MedicineDispensingLine {

    private ProductID med;
    private boolean acquired;


    public MedicineDispensingLine(ProductID med) {
        this.acquired = false;
        this.med = med;
    }

    public boolean getAcquired() {
        return this.acquired;
    }

    public void setAcquired(boolean acquired) {
        this.acquired = acquired;
    }

    public ProductID getMed() {
        return this.med;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicineDispensingLine that = (MedicineDispensingLine) o;
        return acquired == that.acquired &&
                med.equals(that.med);
    }

    @Override
    public int hashCode() {
        return Objects.hash(med, acquired);
    }
}
