package grumpygordon.exceptions;

/**
 * The GrumpyGordonInitialisationException class is a custom exception class
 * that is thrown when the program fails to initialise.
 */
public class GrumpyGordonInitialisationException extends GrumpyGordonException {
    /**
     * Constructor for GrumpyGordonInitialisationException.
     *
     * @param message The error message.
     */
    public GrumpyGordonInitialisationException(String message) {
        super(message);
    }
}
