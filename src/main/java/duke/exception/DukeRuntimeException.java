package duke.exception;

/**
 * Enclosing exception class for all exceptions that arise when running the program.
 */
public class DukeRuntimeException extends RuntimeException {
    public DukeRuntimeException(String message) {
        super(message);
    }
}
