package duke.exceptions;

/**
 * Encapsulates exceptions specific to Duke.
 */
public class DukeException extends Exception {

    /**
     * Constructs a DukeException object with the given message.
     *
     * @param message The message of the exception.
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Constructs a DukeException object with the given message and cause.
     *
     * @param message The message of the exception.
     * @param cause The cause of the exception.
     */
    public DukeException(String message, Throwable cause) {
        super(message, cause);
    }
}
