package duke.exception;

/**
 * An exception that arises when the marking of task cannot be done.
 */
public class InvalidMarkingException extends DukeException {
    /**
     * Creates an duke.exception.InvalidMarkingException instance.
     *
     * @param s Message of the exception.
     */
    public InvalidMarkingException(String s) {
        super(s);
    }
}
