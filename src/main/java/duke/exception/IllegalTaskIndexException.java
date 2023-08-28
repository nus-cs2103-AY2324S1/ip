package duke.exception;

/**
 * duke.exception.IllegalTaskIndexException class is a custom duke.exception class that extends DukeException.
 * It is thrown when the user inputs an invalid duke.task index.
 * @author Alan Lim
 */
public class IllegalTaskIndexException extends DukeException {
    /**
     * Constructor for duke.exception.IllegalTaskIndexException.
     */
    public IllegalTaskIndexException() {
        super("☹ OOPS!!! The duke.task index is invalid.");
    }
}
