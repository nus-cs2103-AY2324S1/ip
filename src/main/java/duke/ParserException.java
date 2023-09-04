package duke;

/**
 * The `ParserException` class is a custom exception that is thrown when an error occurs during parsing user input in the Duke application.
 * This exception is used to handle parsing-related errors.
 */
public class ParserException extends Exception {

    /**
     * Constructs a new `ParserException` with the specified detail message.
     *
     * @param message The detail message indicating the reason for the exception.
     */
    public ParserException(String message) {
        super(message);
    }
}
