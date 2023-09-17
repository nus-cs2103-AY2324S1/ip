package dukeexception;

/**
 * An extension of DukeException, that represents a recognized command with bad arguments.
 */
public class InvalidVarException extends DukeException {
    /**
     * Constructs an InvalidVarException.
     */
    public InvalidVarException() {
        super();
    }
    /**
     * Constructs an InvalidVarException with a message.
     * @param s the associated message.
     */
    public InvalidVarException(String s) {
        super(s);
    }
}
