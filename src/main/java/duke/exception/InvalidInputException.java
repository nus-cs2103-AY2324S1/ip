package duke.exception;

/**
 * An exception thrown when the given instruction is not identifiable.
 */
public class InvalidInputException extends DukeException {
    /**
     * Constructs an InvalidInputException instance.
     *
     * @param s Message of the exception.
     */
    public InvalidInputException(String s) {
        super(s);
    }
}
