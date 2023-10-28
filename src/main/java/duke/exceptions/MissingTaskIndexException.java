package duke.exceptions;

/**
 * An exception class that represents a missing task index in a task list.
 * This exception is thrown when an operation expects a valid task index but none is provided.
 */
public class MissingTaskIndexException extends Exception {
    /**
     * Constructs a duke.exceptions.MissingTaskIndexException with the specified detail message.
     *
     * @param message The detail message explaining the cause of the exception.
     */
    public MissingTaskIndexException(String message) {
        super(message);
    }
}
