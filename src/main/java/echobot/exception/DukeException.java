package echobot.exception;

/**
 * Represents an exception.
 * This exception is thrown when there's an error.
 */
public class DukeException extends Exception {
    /**
     * Constructs a DukeException with the given error message.
     *
     * @param errorMsg Error message associated with the exception.
     */
    public DukeException(String errorMsg) {
        super(errorMsg);
    }

}
