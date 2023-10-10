package duke.exception;

/**
 * duke.exception.IllegalTaskIndexException class is a custom exception class that extends DukeException.
 * It is thrown when the user inputs an invalid task index.
 * @author Alan Lim
 */
public class IllegalTaskIndexException extends DukeException {
    /**
     * Constructor for IllegalTaskIndexException.
     */
    public IllegalTaskIndexException() {
        super("☹ OOPS!!! The duke.task index is invalid.");
    }
}
