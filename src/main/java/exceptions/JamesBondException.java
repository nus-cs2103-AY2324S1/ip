package exceptions;
/**
 * The `JamesBondException` class represents a custom exception used in the Duke application.
 * It is a subclass of the `Exception` class and is used to handle application-specific exceptions.
 */
public class JamesBondException extends Exception {
    /**
     * Constructs a `JamesBondException` object with the specified error message.
     *
     * @param message The error message associated with the exception.
     */
    public JamesBondException(String message) {
        super(message);
    }
}
