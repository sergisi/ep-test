package exceptions.pharmacy;

public class DispensingNotAvailableException extends Exception {
    public DispensingNotAvailableException(String s) {
        super(s);
    }

    public DispensingNotAvailableException() {
        super();
    }
}
