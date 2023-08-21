package exception;

/**
 * exception.IllegalTaskIndexException class is a custom exception class that extends DukeException.
 * It is thrown when the user inputs an invalid task index.
 * @author Alan Lim
 */
public class IllegalTaskIndexException extends DukeException {
    /**
     * Constructor for exception.IllegalTaskIndexException.
     */
    public IllegalTaskIndexException() {
        super("☹ OOPS!!! The task index is invalid.");
    }
}
