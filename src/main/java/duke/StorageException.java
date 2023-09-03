package duke;

/**
 * Signals that an error has occured while saving or loading data from a file.
 */
public class StorageException extends DukeException {
    public StorageException(String msg) {
        super(msg);
    }
}
