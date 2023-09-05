package dukeapp.exceptions;

/**
 * Represents an exception when an unknown command is passed in.
 */
public class UnknownCommandException extends IllegalArgumentException {
    public UnknownCommandException(String message) {
        super(message);
    }
}
