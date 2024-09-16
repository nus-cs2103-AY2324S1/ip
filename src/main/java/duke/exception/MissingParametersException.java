package duke.exception;

/** Exception for missing parameters */
public class MissingParametersException extends DukeException {
    /**
     * Initialize Missing Parameters Exception.
     *
     * @param message Error message to print out.
     */
    public MissingParametersException(String message) {
        super(message);
    }
}
