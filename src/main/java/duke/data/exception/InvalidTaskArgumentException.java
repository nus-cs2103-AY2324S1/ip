package duke.data.exception;

/**
 * Represents an exception due to invalid task arguments.
 */
public class InvalidTaskArgumentException extends DukeException {
    /**
     * Returns an instance of {@code DukeException} with the given error message.
     *
     * @param message The  error message of the exception.
     */
    public InvalidTaskArgumentException(String message) {
        super(message);
    }
}
