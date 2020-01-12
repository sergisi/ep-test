package exceptions.pharmacy;

public class MedicineNotInDispensingListException extends Exception {
    public MedicineNotInDispensingListException(String s) {
        super(s);
    }
}
