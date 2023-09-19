package duke.exceptions;

/**
 * This exception is thrown when no task id is provided when attempting actions
 * such as delete, mark and unmark.
 */
public class NoTaskIdException extends DukeException {
    public NoTaskIdException() {
        super("(・´з`・) Uh oh... please provide a taskID");
    }
}
