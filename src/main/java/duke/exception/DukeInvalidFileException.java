package duke.exception;

/**
 * Exceptions that arise when the file is corrupted.
 */
public class DukeInvalidFileException extends DukeRuntimeException {
    public DukeInvalidFileException(String message) {
        super(message);
    }
}
