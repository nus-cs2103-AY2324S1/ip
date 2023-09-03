package duke.exception;
/**
 * Error when given task number is outside tasklist range.
 */
public class InvalidTaskNumberException extends DukeException {
    /**
     * Constructor for an InvalidTaskNumberException.
     * @param errorMessage message to be displayed when error is thrown
     */
    public InvalidTaskNumberException(String errorMessage) {
        super(errorMessage);
    }

}
