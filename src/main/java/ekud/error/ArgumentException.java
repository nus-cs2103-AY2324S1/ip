package ekud.error;

/**
 * Represents an error with the arguments passed in a command.
 */
public final class ArgumentException extends EkudException {
    /**
     * Creates a new argument exception.
     * 
     * @param message The message to display to the user.
     */
    public ArgumentException(String message) {
        super(message);
    }
}
