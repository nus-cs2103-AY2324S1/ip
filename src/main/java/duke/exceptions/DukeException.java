package duke.exceptions;

/**
 * A RuntimeException that is thrown by Duke.
 * All exceptions thrown by Duke should be a subclass of DukeException.
 */
public class DukeException extends RuntimeException {

    /**
     * A RuntimeException that is thrown by Duke.
     *
     * @param message The message to be displayed when the exception is thrown.
     */
    public DukeException(String message) {
        super(message);
    }
}
