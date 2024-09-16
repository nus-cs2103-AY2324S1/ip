package bob.exceptions;

/**
 * Represents an exception when the event dates are invalid.
 */
public class BobInvalidEventDateException extends BobException {

    /**
     * Constructor for this exception.
     */
    public BobInvalidEventDateException() {
        super("Eyyy, are you sure your event ends after it starts?");
    }
}
