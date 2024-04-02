package dukeexception;

/**
 * An extension of DukeException, that represents a failure in execution of an Executable.
 */
public class FailureInExecuteException extends DukeException {

    /**
     * Constructs a FailureInExecuteException.
     */
    public FailureInExecuteException() {
        super();
    }
    /**
     * Constructs a FailureInExecuteException with a message.
     * @param s the associated message.
     */
    public FailureInExecuteException(String s) {
        super(s);
    }
}
