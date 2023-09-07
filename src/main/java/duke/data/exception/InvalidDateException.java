package duke.data.exception;

/**
 * Represents an exception due to an invalid date.
 */
public class InvalidDateException extends DukeException {

    /**
     * Returns an instance of {@code DukeException} with the given error message.
     *
     */
    public InvalidDateException() {
        super("The dates must be filled in \"yyyy-mm-dd\" format.");
    }
}
