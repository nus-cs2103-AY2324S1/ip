package dukeapp.exceptions;

/**
 * Represents an exception when there are insufficient arguments when creating a
 * task.
 */
public class InsufficientArgumentsException extends IllegalArgumentException {
    public InsufficientArgumentsException(String message) {
        super(message);
    }
}
