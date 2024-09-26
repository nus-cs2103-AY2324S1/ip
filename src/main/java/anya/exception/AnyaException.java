package anya.exception;

/**
 * Custom exception class for handling Anya-specific exceptions.
 *
 * This class extends the standard Java Exception class to create custom exceptions tailored for the Anya application.
 * It allows for the propagation of Anya-specific error messages when exceptional conditions occur during execution.
 *
 * @see Exception
 */
public class AnyaException extends Exception {
    /**
     * Constructs a new instance of the {@code AnyaException} class with the specified error message.
     *
     * @param message A descriptive error message explaining the exceptional condition.
     */
    public AnyaException(String message) {
        super(message);
    }
}
