package joi.utils;

public class InvalidCommandException extends Exception {
    private final String wrongInput;
    public InvalidCommandException (String e) {
        super(e);
        this.wrongInput = e;
    }

    public String getWrongInput() {
        return this.wrongInput;
    }
}
