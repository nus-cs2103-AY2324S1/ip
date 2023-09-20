package duke.data.exception;

/**
 * The DukeException class is a custom Exception class
 * for Duke when the user enters an invalid input.
 */
public class DukeException extends Exception {
    /**
     * Constructor to initialize DukeException.
     *
     * @param msg Message of error thrown.
     */
    public DukeException(String msg) {
        super(msg);
    }
}
