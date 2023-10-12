package duke.exception;

/** General Exception created for the Duke Application */
public class DukeException extends Exception {
    /**
     * Initialize General Exception.
     *
     * @param message Error message to print out.
     */
    public DukeException(String message) {
        super(message);
    }
}
