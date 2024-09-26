package duke.command;

/**
 * The DukeException class represents an exception specific to the Duke application.
 * It extends the base Exception class and provides a custom message for the exception.
 */
public class DukeException extends Exception {
    /**
     * Constructs a DukeException with the specified error message.
     *
     * @param message The error message associated with the exception.
     */
    public DukeException(String message) {
        super(message);
    }
}
