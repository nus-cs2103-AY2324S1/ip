package duke.exceptions;

/**
 * This exception is thrown when the task id provided is out of range
 * when attempting actions such as delete, mark and unmark.
 */
public class InvalidTaskIdException extends DukeException {

    public InvalidTaskIdException() {
        super("(・´з`・) Uh oh... invalid taskID");
    }
}
