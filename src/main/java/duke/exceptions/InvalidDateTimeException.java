package duke.exceptions;

/**
 * An exception class that represents an invalid task description in a task management application.
 * This exception is thrown when an operation encounters an invalid or empty task description.
 */
public class InvalidDateTimeException extends Exception {
    /**
     * Constructs an duke.exceptions.InvalidDateTimeException with the specified detail message.
     *
     * @param message The detail message explaining the cause of the exception.
     */
    public InvalidDateTimeException(String message) {
        super(message);
    }
}
