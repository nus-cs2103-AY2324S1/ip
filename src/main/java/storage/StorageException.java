package storage;
/**
 * Thrown when there is an error
 * related to saving and loading files
 * @author Alvis Ng (supermii2)
 */
public class StorageException extends RuntimeException {
    public StorageException(String message) {
        super(message);
    }
}
