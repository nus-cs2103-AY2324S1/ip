package duke.exception;

/**
 * The duke.exception.DukeException class represents a custom exception used in the duke.Duke class.
 */
public class DukeException extends Exception {
    /**
     * Constructor for duke.exception.DukeException class.
     *
     * @param msg The error message of the exception.
     */
    public DukeException(String msg) {
        super(msg);
    }
}
