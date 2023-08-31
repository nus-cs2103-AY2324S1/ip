package duke.exception;

/**
 * Exception thrown when an invalid index is used to access a list.
 */
public class InvalidIndexException extends Exception {
    /**
     * Constructs an duke.exception.InvalidIndexException with a default error message.
     */
    public InvalidIndexException() {
        super("☹ OOPS!!! This index is out of the list's bounds.");
    }
}
