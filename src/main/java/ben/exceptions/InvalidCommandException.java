package ben.exceptions;

/**
 * Throws error when command is invalid.
 */
public class InvalidCommandException extends Exception {
    /**
     * Takes is a message.
     *
     * @param message The message of the error.
     */
    public InvalidCommandException(String message) {
        super(message);
    }
}
