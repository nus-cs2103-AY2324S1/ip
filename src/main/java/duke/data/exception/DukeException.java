package duke.data.exception;

/**
 * The DukeException class is a custom Exception class
 * for Duke when the user enters an invalid input.
 */
public class DukeException extends Exception {
    /**
     * Constructs a new DukeException with the specified error message.
     *
     * @param msg The error message describing the exception.
     */
    public DukeException(String msg) {
        super(msg);
    }
}
