package dialogix.exception;

/**
 * Represents a custom exception specific to the Dialogix application.
 * This exception is used to handle application-specific errors and exceptional conditions.
 */
public class DialogixException extends Exception {
    /**
     * Constructs a DialogixException with the specified error message.
     *
     * @param message The error message associated with the exception.
     */
    public DialogixException(String message) {
        super(message);
    }
}
