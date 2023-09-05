package seedu.duke.exceptions;

/**
 * NotIntegerException
 */
public class NotIntegerException extends Exception {
    public NotIntegerException() {
        super("NotIntegerException: Not an Integer\n");
    }

    @Override
    public String getMessage() {
        return "Not an Integer\n";
    }
}
