package duke.exceptions;

/**
 * This exception is thrown when a task id provided is not in range.
 */
public class InvalidTaskIdException extends DukeException {

    public InvalidTaskIdException() {
        super("(・´з`・) Uh oh... invalid taskID");
    }
}
