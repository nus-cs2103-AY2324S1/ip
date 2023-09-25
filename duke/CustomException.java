package duke;

/**
 * A custom exception class that extends the base Exception class. It can be used to represent and handle
 * custom exceptions in the Duke chatbot application.
 */
public class CustomException extends Exception {

    /**
     * Constructs a new CustomException with a default error message.
     */
    public CustomException() {
        super("Default custom exception message");
    }

    /**
     * Constructs a new CustomException with a specified error message.
     *
     * @param message The custom error message associated with this exception.
     */
    public CustomException(String message) {
        super(message);
    }
}

