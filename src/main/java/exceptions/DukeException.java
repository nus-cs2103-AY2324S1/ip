package exceptions;

/**
 * The `DukeException` class represents a custom exception specific to the Duke application.
 * It is used to indicate exceptional situations or errors within the application.
 */
public class DukeException extends RuntimeException {
    /**
     * Constructs a new DukeException with the specified error message.
     *
     * @param message The error message describing the exception.
     */
    public DukeException(String message) {
        super(message);
    }
}
