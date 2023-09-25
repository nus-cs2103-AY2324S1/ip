package exceptions;

/**
 * The `WoofStorageException` class represents a custom exception specific to the Woof application.
 * It is used to indicate exceptions related to storage operations,
 * such as reading from or writing to storage.
 */
public class WoofStorageException extends WoofException {
    /**
     * Constructs a new `WoofStorageException` with the specified error message.
     *
     * @param message The error message describing the storage-related exception.
     */
    public WoofStorageException(String message) {
        super(String.format("storage... %s", message));
    }
}
