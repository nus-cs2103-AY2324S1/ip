package duke;

/**
 * Represents an exception specific to the Duke application, indicating an error or issue.
 */
public class DukeException extends Exception {

    /**
     * Constructs a DukeException with the specified error message.
     *
     * @param message The error message indicating the reason for the exception.
     */
    public DukeException(String message) {
        super(":( OOPS!!! " + message);
    }
}
