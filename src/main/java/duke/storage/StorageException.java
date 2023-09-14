package duke.storage;

import duke.DukeException;

/**
 * An exception to be thrown when an error occurs during a storage operation.
 */
public class StorageException extends DukeException {
    public StorageException(String message) {
        super(message);
    }
}
