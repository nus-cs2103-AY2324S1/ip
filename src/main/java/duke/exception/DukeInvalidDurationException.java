package duke.exception;

/**
 * Represents an exception that is thrown when an invalid duration is encountered in the Duke application.
 * This exception is typically used when parsing event durations.
 */
public class DukeInvalidDurationException extends DukeException {
    /**
     * Constructs a new DukeInvalidDurationException with the specified error message.
     *
     * @param message The error message that describes the exception.
     */
    public DukeInvalidDurationException(String message) {
        super(message);
    }
}

