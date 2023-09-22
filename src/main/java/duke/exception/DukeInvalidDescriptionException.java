package duke.exception;

/**
 * Represents an exception that is thrown when an invalid description is encountered in the Duke application.
 * This exception is typically used when parsing task descriptions.
 */
public class DukeInvalidDescriptionException extends DukeException {
    /**
     * Constructs a new DukeInvalidDescriptionException with the specified error message.
     *
     * @param message The error message that describes the exception.
     */
    public DukeInvalidDescriptionException(String message) {
        super(message);
    }
}

