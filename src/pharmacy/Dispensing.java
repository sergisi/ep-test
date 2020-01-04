package pharmacy;

import exceptions.DispensingNotAvailableException;
import data.*;
import exceptions.MedicineAlreadyDispencedException;
import exceptions.MedicineNotInDispensingListException;
import exceptions.NotAllMedicamentsDispensedException;

import java.util.*;


public class Dispensing {
    private byte nOrder;
    private Date initDate, finalDate;
    private boolean isCompleated;
    HashMap<ProductID,MedicineDispensingLine> medicines;


    public Dispensing(byte nOrder, Date initDate, Date finalDate, HashMap<ProductID,MedicineDispensingLine> medicines) {
        this.nOrder=nOrder;
        this.initDate=initDate;
        this.finalDate=finalDate;
        this.isCompleated=false;
        this.medicines=medicines;
    }

    public boolean dispensingEnabled() throws DispensingNotAvailableException{
        Date todayDate = new Date();
        if(todayDate.after(initDate)){
            return true;
        }else{
            throw new DispensingNotAvailableException("Today's Date is before than the start of the treatment one");
        }
    }

    public void setProductAsDispensed(ProductID prodID) throws MedicineNotInDispensingListException, MedicineAlreadyDispencedException {
        if(medicines.containsKey(prodID)){
            MedicineDispensingLine medicament = medicines.get(prodID);
            if(!medicament.getAcquired()){
                medicament.setAcquired(true);
            }else{
                throw new MedicineAlreadyDispencedException("This medicine has been already dispensed");
            }
        }else{
            throw new MedicineNotInDispensingListException("This medicine isn't in the dispensing list of the patient");
        }
    }

    public boolean getCompleted(){
        return this.isCompleated;
    }

    public void setCompleated() throws NotAllMedicamentsDispensedException {
        Collection<MedicineDispensingLine> listOfMedicines = medicines.values();
        Iterator<MedicineDispensingLine> iterMedicines = listOfMedicines.iterator();
        while(iterMedicines.hasNext()){
            if(iterMedicines.next().getAcquired()!=true){
                throw new NotAllMedicamentsDispensedException("Not all medicaments have been dispensed");
            }
        }
        this.isCompleated=true;
    }
    // TODO: rest of the getters and setters (maybe for tests)

    public byte getnOrder(){
        return this.nOrder;
    }

    public void setnOrder(byte nOrder){
        this.nOrder=nOrder;
    }

    public Date getInitDate(){
        return this.initDate;
    }

    public void setInitDate(Date initDate){
        this.initDate=initDate;
    }

    public Date getFinalDate(){
        return this.finalDate;
    }

    public void setFinalDate(Date finalDate){
        this.finalDate=finalDate;
    }

    public HashMap<ProductID,MedicineDispensingLine> getMedicines(){
        return this.medicines;
    }

    public void setMedicines(HashMap<ProductID, MedicineDispensingLine> medicines) {
        this.medicines = medicines;
    }

}
