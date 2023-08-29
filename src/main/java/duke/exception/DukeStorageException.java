package duke.exception;

/**
 * Represents an exception that occurs when an error occurs during any storage activities of Duke.
 */
public class DukeStorageException extends DukeException {
    /**
     * Constructs a DukeStorageException with the specified detail message.
     *
     * @param message The detail message.
     */
    public DukeStorageException(String message) {
        super(message);
    }
}
