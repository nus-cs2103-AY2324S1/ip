package duke.exception;

/**
 * Represents a custom exception that is specific to the chatbot.
 */
public class DukeException extends Exception {

    /**
     * Constructs a new duke.exception.DukeException with the specified message.
     *
     * @param message The message, which can be retrieved by the {@link #getMessage()} method.
     */
    public DukeException(String message) {
        super(message);
    }
}