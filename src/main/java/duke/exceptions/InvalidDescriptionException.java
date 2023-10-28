package duke.exceptions;

/**
 * An exception class that represents an invalid task description in a task management application.
 * This exception is thrown when an operation encounters an invalid or empty task description.
 */
public class InvalidDescriptionException extends Exception {
    /**
     * Constructs an duke.exceptions.InvalidDescriptionException with the specified detail message.
     *
     * @param message The detail message explaining the cause of the exception.
     */
    public InvalidDescriptionException(String message) {
        super(message);
    }
}
