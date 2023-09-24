package duke.exception;

/**
 * Represents an exception thrown when an unknown command is encountered in Duke.
 */
public class UnknownCommandException extends DukeException {
    public UnknownCommandException(String message) {
        super(message);
    }
}
