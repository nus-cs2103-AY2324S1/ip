package extensions.exceptions;

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
