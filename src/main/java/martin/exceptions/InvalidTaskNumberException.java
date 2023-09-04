package martin.exceptions;

/**
 * Represents an exception when the given task number is invalid.
 *
 * @param message Error message to be printed out.
 */
public class InvalidTaskNumberException extends MartinException {
    public InvalidTaskNumberException(String message) {
        super(message);
    }
}