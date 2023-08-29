package duke.exception;

/**
 * Exception thrown when the finding command is invalid.
 */
public class InvalidFindingException extends DukeException{
    /**
     * Creates an InvalidFindingException instance.
     *
     * @param s Message of the exception.
     */
    public InvalidFindingException(String s) {
        super(s);
    }
}
