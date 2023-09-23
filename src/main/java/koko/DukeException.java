package koko;

/**
 * Represents an exception that is specific to the Duke chatbot application.
 */
public class DukeException extends Exception {

    /**
     * Constructs a DukeException with the specified error message.
     *
     * @param message The error message to be displayed.
     */
    public DukeException(String message) {
        super(message);
    }
}
