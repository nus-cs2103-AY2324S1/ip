package dukeapp.exceptions;

/**
 * Represents an exception when an unknown command is passed in.
 */
public class UnknownCommandException extends DukeException {
    public UnknownCommandException(String message) {
        super(message);
    }
}
