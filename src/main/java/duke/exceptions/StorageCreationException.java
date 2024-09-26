package duke.exceptions;

/**
 * Represents an exception when creating the storage directory or storage file fails.
 */
public class StorageCreationException extends Exception {
    public StorageCreationException(String message) {
        super(message);
    }
}
