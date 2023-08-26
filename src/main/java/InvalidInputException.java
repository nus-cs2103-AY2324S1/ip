/**
 * An exception that arises when the given instruction is not identifiable.
 */
public class InvalidInputException extends DukeException{

    /**
     * Creates an InvalidInputException instance.
     *
     * @param s Message of the exception.
     */
    public InvalidInputException(String s) {
        super(s);
    }
}
