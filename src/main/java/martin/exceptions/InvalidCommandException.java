package martin.exceptions;

/**
 * Represents an exception when command is invalid.
 *
 * @param message Error message to be printed out.
 */
public class InvalidCommandException extends MartinException {
    public InvalidCommandException(String message) {
        super(message);
    }
}