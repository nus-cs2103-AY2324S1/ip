package arona.exception;

/**
 * The `IllegalArgumentAronaException` is a custom exception class for handling invalid arguments in Arona.
 * It is thrown when an operation receives an invalid or unexpected argument.
 */
public class IllegalArgumentAronaException extends Exception {

    /**
     * Constructs a new `IllegalArgumentAronaException` with the specified detail message.
     *
     * @param message The detail message explaining the cause of the exception.
     */
    public IllegalArgumentAronaException(String message) {
        super(message);
    }
}

