package duke;
/**
 * Represents exceptions specific to Duke, the Personal Assistant Chatbot.
 */
public class DukeException extends Exception {
    /**
     * Constructs a DukeException with the specified error message.
     *
     * @param message The error message associated with this exception.
     */
    public DukeException(String message) {
        super(message);
    }
}
