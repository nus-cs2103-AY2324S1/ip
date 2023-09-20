package friday.exception;
/**
 * Represents an exception when an unrecognized command is encountered in the Friday application.
 */
public class InvalidCommandException extends FridayException {
    /**
     * Constructs a new InvalidCommandException with the specified detail message.
     *
     * @param message The detail message.
     */
    public InvalidCommandException(String message) {
        super(message);
    }
}
