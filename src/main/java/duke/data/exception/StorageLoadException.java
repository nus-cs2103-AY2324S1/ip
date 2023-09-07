package duke.data.exception;

/**
 * Represents an exception due to invalid storage data.
 */
public class StorageLoadException extends DukeException {

    /**
     * Returns an instance of {@code DukeException} with the given error message.
     *
     * @param message The error message of the exception.
     */
    public StorageLoadException(String message) {
        super(message);
    }
}
