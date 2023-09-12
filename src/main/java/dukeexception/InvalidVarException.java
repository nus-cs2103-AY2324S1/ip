package dukeexception;

/**
 * An extension of DukeException, that represents a recognized command with bad arguments.
 */
public class InvalidVarException extends DukeException {
    public InvalidVarException() {
        super();
    }
    public InvalidVarException(String s) {
        super(s);
    }
}
