package grumpygordon.exceptions;

/**
 * The GrumpyGordonInvalidCommandException class is a custom exception class
 * that is thrown when the user inputs an invalid command.
 */
public class GrumpyGordonInvalidCommandException extends GrumpyGordonException {
    /**
     * Constructor for GrumpyGordonInvalidCommandException.
     *
     * @param message The error message.
     */
    public GrumpyGordonInvalidCommandException(String message) {
        super(message);
    }
}
