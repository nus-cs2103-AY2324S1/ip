package duke.exceptions;

/**
 * Custom exception class for out-of-index task access in the Duke application.
 * This exception is thrown when a user attempts to access a task using an index that is out of the valid range.
 */
public class OutOfIndexException extends DukeException {

    /**
     * Constructs a new OutOfIndexException with a predefined error message.
     * The error message informs the user about the fact that they provided an invalid task index.
     */
    public OutOfIndexException() {
        super("You have provided a number out of index of the stored tasks");

    }
}
