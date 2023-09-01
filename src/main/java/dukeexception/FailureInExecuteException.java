package dukeexception;

/**
 * An extension of DukeException, that represents a failure in execution of a Commandable.
 */
public class FailureInExecuteException extends DukeException {

    public FailureInExecuteException() {
        super();
    }
    public FailureInExecuteException(String s) {
        super(s);
    }
}
