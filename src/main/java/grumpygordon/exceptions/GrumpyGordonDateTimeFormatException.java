package grumpygordon.exceptions;

/**
 * The GrumpyGordonDateTimeFormatException class is a custom exception class
 * that is thrown when the user inputs a date and time in the wrong format.
 */
public class GrumpyGordonDateTimeFormatException extends GrumpyGordonException {
    /**
     * Constructor for GrumpyGordonDateTimeFormatException.
     *
     * @param message The error message.
     */
    public GrumpyGordonDateTimeFormatException(String message) {
        super(message);
    }
}
