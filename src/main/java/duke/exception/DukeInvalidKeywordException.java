package duke.exception;

/**
 * Represents an exception that is thrown when an invalid keyword is encountered in the Duke application.
 * This exception is typically used when parsing user commands that require keywords.
 */
public class DukeInvalidKeywordException extends DukeException {
    /**
     * Constructs a new DukeInvalidKeywordException with the specified error message.
     *
     * @param message The error message that describes the exception.
     */
    public DukeInvalidKeywordException(String message) {
        super(message);
    }
}

