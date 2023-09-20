package seedu.duke.exceptions;

/**
 * NotIntegerException
 */
public class NotIntegerException extends Exception {
    /**
     * Exception for input not being an Integer
     */
    public NotIntegerException() {
        super("NotIntegerException: Not an Integer\n");
    }

    @Override
    public String getMessage() {
        return "Not an Integer\n";
    }
}
