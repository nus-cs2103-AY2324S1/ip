package duke.exception;

/**
 * Exception thrown when an index is input in the wrong format.
 */
public class InvalidIndexException extends DukeException {

    /**
     * Constructor for InvalidIndexException.
     */
    public InvalidIndexException() {
        super("☹ OOPS!!! Please enter a valid positive integer for the index.");
    }

}
