package duke.exceptions;

/**
 * Represents a custom exception specific to the Duke application.
 */
public class DukeException extends Exception {

    /**
     * Constructs a DukeException with the specified error message.
     *
     * @param message The error message to be associated with the exception.
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Overrides the getMessage() method to prepend "OOPS! " to the error message.
     *
     * @return The formatted error message.
     */
    @Override
    public String getMessage() {
        return "OOPS! " + super.getMessage();
    }
}
