package grumpygordon.exceptions;

/**
 * The GrumpyGordonException class is a generic custom exception class
 * that for subclassing purposes.
 */
public class GrumpyGordonException extends Exception {
    /**
     * Constructor for GrumpyGordonException.
     *
     * @param message The error message.
     */
    public GrumpyGordonException(String message) {
        super(message);
    }
}

