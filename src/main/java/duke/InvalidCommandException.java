package duke;

/**
 * The `InvalidCommandException` class is a custom exception that is thrown when an invalid command is encountered in the Duke application.
 * This exception is used to handle cases where the user provides an unrecognized command.
 */
public class InvalidCommandException extends Exception {

    /**
     * Constructs a new `InvalidCommandException` with the specified detail message.
     *
     * @param message The detail message indicating the reason for the exception.
     */
    public InvalidCommandException(String message) {
        // Call the constructor of the parent class (Exception) and provide the error message.
        super(message);
    }
}
