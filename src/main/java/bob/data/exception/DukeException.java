package bob.data.exception;

/**
 * Represents an error specific to Duke.
 */
public class DukeException extends Exception {
    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message The detail message.
     */
    public DukeException(String message) {
        super(message);
    }
}
