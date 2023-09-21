package duke.exceptions;

/**
 * Custom exception class for Duke application.
 * This class extends the built-in
 * Exception class and allows creating exceptions with specific error messages.
 */
public class DukeException extends Exception {
    /**
     * Constructs a new DukeException with the specified error message.
     *
     * @param message The error message describing the exceptional situation.
     */
    public DukeException(String message) {
        super(message);
    }
}
