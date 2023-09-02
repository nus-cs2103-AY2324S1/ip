package duke.exception;

/**
 * Represents an exception thrown when there's a storage related issue in Duke.
 */
public class DukeStorageException extends DukeException {
    public DukeStorageException(String message) {
        super(message);
    }
}
