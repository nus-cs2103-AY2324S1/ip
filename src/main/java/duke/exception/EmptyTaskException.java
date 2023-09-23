package duke.exception;

/**
 * The duke.exception.EmptyTaskException class represents a custom exception used in the duke.Duke class.
 * It extends the duke.exception.DukeException class.
 * It is thrown when the description of a task is empty.
 */
public class EmptyTaskException extends DukeException {
    /**
     * Constructor for duke.exception.EmptyTaskException class.
     *
     * @param msg The type of task for which the description is left empty.
     */
    public EmptyTaskException(String msg) {
        super("☹ OOPS!!! The description of a " + msg + " cannot be empty.");
    }
}
