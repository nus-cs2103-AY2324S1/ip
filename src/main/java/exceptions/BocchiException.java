package exceptions;

/**
 * Super class for custom exceptions used in Bocchi.
 */
public abstract class BocchiException extends Exception {
    /**
     * Constructs a new BocchiException with the specified detail message.
     *
     * @param message The detail message associated with the exception.
     */
    public BocchiException(String message) {
        super(message);
    }
}
