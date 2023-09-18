package echobot.exception;

/**
 * Represents an exception.
 * This exception is thrown when there's an error.
 */
public class EchoBotException extends Exception {
    /**
     * Constructs a DukeException with the given error message.
     *
     * @param errorMsg Error message associated with the exception.
     */
    public EchoBotException(String errorMsg) {
        super(errorMsg);
    }

}
