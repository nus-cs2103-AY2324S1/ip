package duke.exception;

/**
 * Signals that an exception related to chatbot has occurred.
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}
