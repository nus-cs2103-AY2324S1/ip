package martin.exceptions;

/**
 * Represents an exception when no task is found.
 *
 * @param message Error message to be printed out.
 */
public class NoTaskFoundException extends MartinException {
    public NoTaskFoundException(String message) {
        super(message);
    }
}