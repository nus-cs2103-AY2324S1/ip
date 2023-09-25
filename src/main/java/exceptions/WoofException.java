package exceptions;

/**
 * The `WoofException` class represents a custom exception specific to the Woof application.
 * It is used to indicate exceptional situations or errors within the application.
 */
public class WoofException extends RuntimeException {
    /**
     * Constructs a new `WoofException` with the specified error message.
     *
     * @param message The error message describing the exception.
     */
    public WoofException(String message) {
        super(String.format("Woof! %s", message));
    }
}
