package alice.exception;

/**
 * Represents a checked exception specific to the Alice application.
 */
public class DukeException extends Exception {
    /**
     * Constructs a DukeException with the given message.
     *
     * @param message The message of the exception.
     */
    public DukeException(String message) {
        super(message);
    }
}
