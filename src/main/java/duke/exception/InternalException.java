package duke.exception;

/**
 * Represents an exception that is due to the implementation of duke, for example, tasklist is implemented
 * with wrongly array of fixed length, so that it cannot store more than that amount of tasks.
 */
public class InternalException extends DukeException {
    public InternalException(String message) {
        super("Internal exception: " + message);
    }
}
