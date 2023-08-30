package duke.exception;

/**
 * Represents a custom exception specific to the Duke application.
 */
public class DukeException extends Exception{
    /**
     * Constructs a DukeException with the specified error message.
     *
     * @param message The error message explaining the exception.
     */
    public DukeException(String message) {
        super(message);
    }
}
