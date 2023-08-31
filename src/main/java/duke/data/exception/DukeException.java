package duke.data.exception;

/**
 * Represents an exception with the duke chatbot.
 */
public class DukeException extends Exception {

    /**
     * Returns an instance of {@code DukeException} with the given error message.
     *
     * @param message The  error message of the exception.
     */
    public DukeException(String message) {
        super(message);
    }
}
