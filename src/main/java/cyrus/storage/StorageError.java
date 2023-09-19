package cyrus.storage;

/**
 * Custom error that occurs when working with an {@code IStorage} instance.
 */
public class StorageError extends Exception {
    /**
     * Constructor for {@code StorageError}.
     *
     * @param reason reason for error.
     */
    public StorageError(String reason) {
        super(String.format("Something went wrong with loading/saving to the storage medium:\n\t%s\n", reason));
    }
}
