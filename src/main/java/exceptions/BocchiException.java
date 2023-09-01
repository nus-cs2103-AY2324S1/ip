package exceptions;

/**
 * Super class for custom exceptions used in Bocchi
 */
public abstract class BocchiException extends Exception {
    public BocchiException(String message) {
        super(message);
    }
}
