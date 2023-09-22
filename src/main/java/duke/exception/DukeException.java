package duke.exception;

/**
 * The base exception class for Duke-related exceptions.
 * Duke exceptions are thrown to indicate errors or exceptional conditions in the Duke application.
 */
public class DukeException extends Exception {
    /**
     * Constructs a new DukeException with the specified error message.
     *
     * @param message The error message that describes the exception.
     */
    public DukeException(String message) {
        super(message);
    }
}

