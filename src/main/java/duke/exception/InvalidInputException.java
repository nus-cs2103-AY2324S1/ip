package duke.exception;

/**
 * An exception that arises when the given instruction is not identifiable.
 */
public class InvalidInputException extends DukeException {

    /**
     * Creates an duke.exception.InvalidInputException instance.
     *
     * @param s Message of the exception.
     */
    public InvalidInputException(String s) {
        super(s);
    }
}
