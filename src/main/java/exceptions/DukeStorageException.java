package exceptions;

/**
 * The `DukeStorageException` class represents a custom exception specific to the Duke application.
 * It is used to indicate exceptions related to storage operations,
 * such as reading from or writing to storage.
 */
public class DukeStorageException extends DukeException {
    /**
     * Constructs a new DukeStorageException with the specified error message.
     *
     * @param message The error message describing the storage-related exception.
     */
    public DukeStorageException(String message) {
        super(message);
    }
}
