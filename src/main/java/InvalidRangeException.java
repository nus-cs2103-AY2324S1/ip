/**
 * The InvalidRangeException class represents a custom exception used in the Duke class.
 * It extends the DukeException class.
 * It is thrown when the user input for mark, unmark and delete of tasks exceed list length.
 */
public class InvalidRangeException extends DukeException {
    /**
     * Constructor for InvalidRangeException class.
     *
     * @param msg The error message of the exception.
     */
    public InvalidRangeException(String msg) {
        super(msg);
    }
}
