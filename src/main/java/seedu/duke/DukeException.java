package seedu.duke;

/**
 * Encapsulates the exceptions that are related to using the Duke Chatbot.
 */
public class DukeException extends Exception {
    /**
     * Creates a new DukeException instance.
     *
     * @param message The message associated with the DukeException.
     */
    public DukeException(String message) {
        super(message);
    }
}
