package friday.exception;
/**
 * Represents an exception when an incorrect task command format is encountered in the Friday application.
 */
public class InvalidTaskFormatException extends InvalidCommandException {
    /**
     * Constructs a new InvalidTaskFormatException with the specified detail message.
     *
     * @param message The detail message.
     */
    public InvalidTaskFormatException(String message) {
        super(message);
    }
}
