package roo;

/**
 * Exception for invalid inputs.
 */
public class RooException extends Exception {
    public RooException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Paiseh... " + this.getMessage();
    }
}
