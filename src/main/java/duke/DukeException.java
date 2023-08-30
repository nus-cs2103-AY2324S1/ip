package duke;

/**
 * The DukeException class represents an exception specific to the Duke application.
 *
 * @author selwyn
 */
public class DukeException extends Exception {
    /**
     * Constructs a DukeException object with the specified error message.
     *
     * @param message The error message to be associated with the exception.
     */
    public DukeException(String message) {
        super(message);
    }
}
