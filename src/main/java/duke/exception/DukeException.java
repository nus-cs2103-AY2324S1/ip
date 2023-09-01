package duke.exception;

/**
 * Represents an exception specific to the Duke application.
 */
public class DukeException extends Exception {

    /**
     * Constructs a new DukeException with the specified error message.
     *
     * @param errorMessage The specific error message for this exception.
     */
    public DukeException(String errorMessage) {
        super("☹ OOPS!!! " + errorMessage);
    }
}
