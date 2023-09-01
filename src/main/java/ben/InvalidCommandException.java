package ben;

/**
 * Error thrown when command is invalid.
 */
public class InvalidCommandException extends Exception {
    /**
     * Constructor that takes is a message.
     *
     * @param message The message of the error.
     */
    public InvalidCommandException(String message) {
        super(message);
    }
}
