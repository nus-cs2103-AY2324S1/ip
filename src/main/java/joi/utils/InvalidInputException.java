package joi.utils;

public class InvalidInputException extends Exception {
    private final String wrongInput;
    public InvalidInputException (String e) {
        super(e);
        this.wrongInput = e;
    }

    public String getWrongInput() {
        return this.wrongInput;
    }
}
