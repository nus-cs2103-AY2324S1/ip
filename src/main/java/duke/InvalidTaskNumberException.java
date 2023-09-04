package duke;

/**
 * The `InvalidTaskNumberException` class is a custom exception that is thrown when an invalid task number is encountered in the Duke application.
 * This exception is used to handle cases where an invalid task number is provided as input.
 */
public class InvalidTaskNumberException extends Exception {

    /**
     * Constructs a new `InvalidTaskNumberException` with the specified detail message.
     *
     * @param message The detail message indicating the reason for the exception.
     */
    public InvalidTaskNumberException(String message) {
        // Call the constructor of the parent class (Exception) and provide the error message.
        super(message);
    }
}
