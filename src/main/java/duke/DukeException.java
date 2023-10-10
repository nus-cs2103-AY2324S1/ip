package duke;

/**
 * DukeException is a custom exception class for handling exceptions
 * specific to the Duke application.
 */
public class DukeException extends Exception {

    /**
     * Constructs a DukeException with the specified error message.
     *
     * @param message The error message associated with this exception.
     */
    public DukeException(String message) {
        super(message);
    }
}
