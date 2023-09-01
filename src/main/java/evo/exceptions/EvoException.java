package evo.exceptions;

/**
 * The `EvoException` class is a custom exception that extends the standard `Exception` class.
 * It is used to indicate exceptions that are specific to the Evo application.
 */
public class EvoException extends Exception {
    /**
     * Constructs an EvoException object with the given error message.
     *
     * @param message The error message that describes the exception.
     */
    public EvoException(String message) {
        super(message);
    }
}
