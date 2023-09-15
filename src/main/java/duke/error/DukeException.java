package duke.error;

/**
 * Represents an exception specific to the Duke application.
 * DukeException is thrown when there's an issue or error within the Duke application.
 */
public class DukeException extends Exception {

    /**
     * Constructs a DukeException with the specified error message.
     *
     * @param message Error message indicating the nature of the exception.
     */
    public DukeException(String message) {
        super(" ☹ OOPS!!! " + message);
    }
}
