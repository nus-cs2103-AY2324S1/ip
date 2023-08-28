package duke.exception;

/**
 * The DukeException is an exception that occurs while running the Duke program.
 */
public class DukeException extends Exception {
    /**
     * The constructor of a DukeException.
     *
     * @param message The error message of the DukeException.
     */
    public DukeException(String message) {
        super(message);
    }
}
