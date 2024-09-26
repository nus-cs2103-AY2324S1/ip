package duke;

/**
 * The DukeException class represents an exception specific to the Duke application.
 * It extends the RuntimeException class to handle custom exceptions for Duke.
 */
public class DukeException extends RuntimeException {
    /**
     * Constructs a DukeException object with the specified error message.
     *
     * @param message The error message associated with the exception.
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Returns a string representation of the DukeException.
     *
     * @return A formatted error message followed by the exception message.
     */
    @Override
    public String toString() {
        return "OOPS! " + super.getMessage();
    }
}
