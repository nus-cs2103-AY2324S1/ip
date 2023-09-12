package duke.exceptions;

/**
 * Special exceptions encountered by the chatbot.
 */
public class DukeException extends Exception {
    /**
     * Constructor for the DukeException class.
     * @param errorMessage Message about the error.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
