package ekud.error;

/**
 * Represents a general exception that is thrown by code from the ekud packages.
 * Adding a catch for this enables recovering from all expected errors e.g.
 * parsing, invalid arguments, file storage.
 */
public class EkudException extends RuntimeException {
    public EkudException(String message) {
        super(message);
    }
}
