package duke.exception;

/**
 * Represents an exception that is thrown when an invalid task index is encountered in the Duke application.
 * This exception is typically used when parsing user commands that require task indices.
 */
public class DukeInvalidTaskIndexException extends DukeException {
    /**
     * Constructs a new DukeInvalidTaskIndexException with the specified error message.
     *
     * @param message The error message that describes the exception.
     */
    public DukeInvalidTaskIndexException(String message) {
        super(message);
    }
}

