package ekud.error;

/**
 * Represents an error with interacting with the file that saves the data from
 * this program.
 */
public final class StorageException extends EkudException {
    /**
     * Creates a new storage exception.
     * 
     * @param message The message to display to the user.
     */
    public StorageException(String message) {
        super(message);
    }
}
