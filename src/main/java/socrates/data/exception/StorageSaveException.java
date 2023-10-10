package socrates.data.exception;

/**
 * Represents an exception while trying to save data to the storage file.
 */
public class StorageSaveException extends SocratesException {

    /**
     * Returns an instance of {@code SocratesException} with the given error message.
     *
     * @param message The error message of the exception.
     */
    public StorageSaveException(String message) {
        super(message);
    }
}
