package duke;

/**
 * The `InvalidTaskCreationException` class is a custom exception that is thrown when an error occurs
 * during the creation of a task in the Duke application.
 * This exception is used to handle cases where task creation fails due to invalid input or other issues.
 */
public class InvalidTaskCreationException extends Exception {

    /**
     * Constructs a new `InvalidTaskCreationException` with the specified detail message.
     *
     * @param message The detail message indicating the reason for the exception.
     */
    public InvalidTaskCreationException(String message) {
        // Call the constructor of the parent class (Exception) and provide the error message.
        super(message);
    }
}
