package chatterchicken.data.exception;

/**
 * The CCException class represents a custom exception specific to the ChatterChicken task manager application.
 * It is used to handle and convey application-specific errors and exceptional conditions.
 */
public class CCException extends Exception {

    /**
     * Constructs a new CCException with the specified error message.
     *
     * @param message The error message associated with the exception.
     */
    public CCException(String message) {
        super(message);
    }
}