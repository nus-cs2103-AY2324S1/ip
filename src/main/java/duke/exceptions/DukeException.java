package duke.exceptions;

/**
 * DukeException class represents a custom exception for Duke program.
 * It extends the base Exception class and provides additional functionality
 * to customize error messages.
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
    @Override
    public String getMessage() {
        return "☹ OOPS!!! ";
    }
}
