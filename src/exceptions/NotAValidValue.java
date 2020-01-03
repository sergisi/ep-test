package exceptions;

public class NotAValidValue extends Exception {
    public NotAValidValue(String s) {
        super(s);
    }
}
