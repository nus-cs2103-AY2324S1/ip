package duke.exception;

/**
 * A Checked Exception that is thrown when there is an error in the Chatbot.
 */
public class DukeException extends Exception {

    /**
     * The Constructor for the Exception class.
     *
     * @param message The message given when the Exception is thrown.
     */
    public DukeException(String message) {
        super(message);
    }
}
