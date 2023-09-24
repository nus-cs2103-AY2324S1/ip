package duke.exception;

/**
 * Represents an exception thrown when a file is found to be corrupted in Duke.
 */
public class FileCorruptedException extends DukeException {
    public FileCorruptedException(String message) {
        super(message);
    }
}
