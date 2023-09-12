package duke.storage;

/**
 * Represents an error when loading from storage file.
 */
public class DukeStorageException extends Exception {
    public DukeStorageException(String message) {
        super(message);
    }
}
