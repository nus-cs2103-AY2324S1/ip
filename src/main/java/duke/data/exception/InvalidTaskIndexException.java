package duke.data.exception;

/**
 * Represents an exception due to an invalid task index.
 */
public class InvalidTaskIndexException extends DukeException {

    /**
     * Returns an instance of {@code DukeException} with the given error message.
     */
    public InvalidTaskIndexException() {
        super("The task must be specified.");
    }
}
