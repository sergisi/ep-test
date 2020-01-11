package pharmacy;

import data.ProductID;
import exceptions.DispensingNotAvailableException;
import exceptions.MedicineAlreadyDispencedException;
import exceptions.MedicineNotInDispensingListException;
import exceptions.NotAllMedicamentsDispensedException;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;


public class Dispensing {
    private byte nOrder;
    private Date initDate, finalDate;
    private boolean isCompleted;
    HashMap<ProductID, MedicineDispensingLine> medicines;


    public Dispensing(byte nOrder, Date initDate, Date finalDate, HashMap<ProductID, MedicineDispensingLine> medicines) {
        this.nOrder = nOrder;
        this.initDate = initDate;
        this.finalDate = finalDate;
        this.isCompleted = false;
        this.medicines = medicines;
    }

    public boolean dispensingEnabled() throws DispensingNotAvailableException {
        Date todayDate = new Date();
        if (todayDate.after(initDate)) {
            return true;
        } else {
            throw new DispensingNotAvailableException("Today's Date is not in the period of treatment.");
        }
    }

    public void setProductAsDispensed(ProductID prodID) throws MedicineNotInDispensingListException, MedicineAlreadyDispencedException {
        if (medicines.containsKey(prodID)) {
            MedicineDispensingLine medicament = medicines.get(prodID);
            if (!medicament.getAcquired()) {
                medicament.setAcquired(true);
            } else {
                throw new MedicineAlreadyDispencedException("This medicine has been already dispensed");
            }
        } else {
            throw new MedicineNotInDispensingListException("This medicine isn't in the dispensing list of the patient");
        }
    }

    public boolean getCompleted() {
        return this.isCompleted;
    }

    public void setCompleated() throws NotAllMedicamentsDispensedException {
        Collection<MedicineDispensingLine> listOfMedicines = medicines.values();
        for (MedicineDispensingLine listOfMedicine : listOfMedicines) {
            if (!listOfMedicine.getAcquired()) {
                throw new NotAllMedicamentsDispensedException("Not all medicaments have been dispensed");
            }
        }
        this.isCompleted = true;
    }

    public byte getnOrder() {
        return this.nOrder;
    }

    public void setnOrder(byte nOrder) {
        this.nOrder = nOrder;
    }

    public Date getInitDate() {
        return this.initDate;
    }

    public void setInitDate(Date initDate) {
        this.initDate = initDate;
    }

    public Date getFinalDate() {
        return this.finalDate;
    }

    public void setFinalDate(Date finalDate) {
        this.finalDate = finalDate;
    }

    public HashMap<ProductID, MedicineDispensingLine> getMedicines() {
        return this.medicines;
    }

    public void setMedicines(HashMap<ProductID, MedicineDispensingLine> medicines) {
        this.medicines = medicines;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dispensing that = (Dispensing) o;
        boolean ret = medicines.equals(that.medicines);
        return nOrder == that.nOrder &&
                isCompleted == that.isCompleted &&
                initDate.equals(that.initDate) &&
                finalDate.equals(that.finalDate) &&
                medicines.equals(that.medicines);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nOrder, initDate, finalDate, isCompleted, medicines);
    }
}
