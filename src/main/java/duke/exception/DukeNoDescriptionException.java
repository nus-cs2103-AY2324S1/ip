package duke.exception;

/**
 * An exception thrown when there is no description for the
 * tasks.
 */
public class DukeNoDescriptionException extends DukeException {

    /**
     * Constructor for the exception.
     * @param msg the task that has no description
     */
    public DukeNoDescriptionException(String msg) {
        super("OOPS!!! The description of a "
                + msg
                + " cannot be empty."
                + "\n");
    }
}
