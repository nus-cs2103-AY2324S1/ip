package duke.exception;

/**
 * Represents a checked exception thrown when the chatbot
 * encountered an error state.
 */
public class DukeException extends Exception {

    /**
     * Constructs a DukeException.
     *
     * @param message The error message to be printed when the exception is caught.
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Constructs a DukeException.
     */
    public DukeException() {
        super();
    }
}
