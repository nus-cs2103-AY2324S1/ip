package duke.exceptions;

/**
 * An exception class that represents an invalid task index in a task list.
 * This exception is thrown when an operation encounters an index that is out of bounds or not valid.
 */
public class InvalidTaskIndexException extends Exception {
    /**
     * Constructs an duke.exceptions.InvalidTaskIndexException with the specified detail message.
     *
     * @param message The detail message explaining the cause of the exception.
     */
    public InvalidTaskIndexException(String message) {
        super(message);
    }
}
