package duke;

/**
 * Represents a custom exception related to the Duke chatbot.
 * Used to handle specific errors that may arise during the operation
 * of the chatbot.
 */
public class DukeException extends Exception {

    /**
     * Constructs a new DukeException with the specified detail message.
     *
     * @param exception The detail message associated with the exception.
     */
    public DukeException(String exception) {
        super(exception);
    }
}
