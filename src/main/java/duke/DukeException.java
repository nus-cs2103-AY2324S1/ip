package duke;

/**
 * Exception class for chatbot errors.
 */
public class DukeException extends Exception {
    /**
     * Constructs a DukeException with an error message.
     *
     * @param message The error message.
     */
    public DukeException(String message) {
        super(message);
    }
}
