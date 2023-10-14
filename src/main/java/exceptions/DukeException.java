package exceptions;

/**
 * A runtime exception thrown by Duke chatbot.
 * All other duke-specific exceptions will extend DukeException.
 */
public class DukeException extends RuntimeException {

    /**
     * Constructor, initialises the error message.
     *
     * @param errorMessage Error message to be shown.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
