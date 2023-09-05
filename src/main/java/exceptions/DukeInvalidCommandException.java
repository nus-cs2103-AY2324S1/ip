package exceptions;

/**
 * The `DukeInvalidCommandException` class represents a custom exception specific to the Duke application.
 * It is used to indicate that an invalid command has been encountered.
 */
public class DukeInvalidCommandException extends DukeException {
    /**
     * Constructs a new DukeInvalidCommandException with the specified error message.
     *
     * @param message The error message describing the invalid command.
     */
    public DukeInvalidCommandException(String message) {
        super(message);
    }
}
