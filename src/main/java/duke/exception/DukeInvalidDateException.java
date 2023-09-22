package duke.exception;

/**
 * Represents an exception that is thrown when an invalid date format is encountered in the Duke application.
 * This exception is typically used when parsing date-related input.
 */
public class DukeInvalidDateException extends DukeException {
    /**
     * Constructs a new DukeInvalidDateException with the specified error message.
     *
     * @param message The error message that describes the exception.
     */
    public DukeInvalidDateException(String message) {
        super(message);
    }
}

