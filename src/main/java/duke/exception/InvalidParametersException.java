package duke.exception;

/** Exception for invalid parameters */
public class InvalidParametersException extends DukeException {
    /**
     * Initialize Invalid Parameters Exception.
     *
     * @param message Error message to print out.
     */
    public InvalidParametersException(String message) {
        super(message);
    }
}
