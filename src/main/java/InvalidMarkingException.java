/**
 * An exception that arises when the marking of task cannot be done.
 */
public class InvalidMarkingException extends DukeException{

    /**
     * Creates an InvalidMarkingException instance.
     *
     * @param s Message of the exception.
     */
    public InvalidMarkingException(String s) {
        super(s);
    }
}
