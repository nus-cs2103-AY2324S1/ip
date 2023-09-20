package bob.exceptions;

/**
 * Represents an exception when the date for LocalDate is invaid.
 */
public class BobInvalidLocalDateException extends BobException {

    /**
     * Constructor for this exception.
     */
    public BobInvalidLocalDateException() {
        super("Eyyyy, your date is in the wrong format!");
    }
}
