package extensions;

/**
 * Represents an error reading or writing to the saved tasks file.
 */
public class EkudIOException extends EkudException {
    public EkudIOException(String message) {
        super(message);
    }
}