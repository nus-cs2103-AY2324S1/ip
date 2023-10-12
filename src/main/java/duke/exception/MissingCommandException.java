package duke.exception;

/** Exception for missing sub command */
public class MissingCommandException extends DukeException {
    /**
     * Initialize Missing Command Exception.
     *
     * @param message Error message to print out.
     */
    public MissingCommandException(String message) {
        super(message);
    }
}
