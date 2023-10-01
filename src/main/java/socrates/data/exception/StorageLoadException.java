package socrates.data.exception;

/**
 * Represents an exception due to invalid storage data.
 */
public class StorageLoadException extends SocratesException {

    /**
     * Returns an instance of {@code SocratesException} with the given error message.
     *
     * @param message The error message of the exception.
     */
    public StorageLoadException(String message) {
        super(message);
    }
}
