package duke.exception;

/**
 * The duke.exception.InvalidRangeException class represents a custom exception used in the duke.Duke class.
 * It extends the duke.exception.DukeException class.
 * It is thrown when the user input for mark, unmark and delete of tasks exceed list length.
 */
public class InvalidRangeException extends DukeException {
    /**
     * Constructor for duke.exception.InvalidRangeException class.
     *
     * @param msg The error message of the exception.
     */
    public InvalidRangeException(String msg) {
        super(msg);
    }
}
