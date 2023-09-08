package duke.exception;

/**
 * An exception thrown when there is no specific date given.
 */
public class DukeNoDateException extends DukeException {
    /**
     * A constructor for exception.
     * @param msg the task which has no date
     */
    public DukeNoDateException(String msg) {
        super("there is no specific/accurate date for "
                + msg
                + "\n");
    }
}
