package seedu.duke.exceptions;

/**
 * The NoTasksException is thrown when the list command is used and there are no tasks in
 * the task list.
 */
public class NoTasksException extends LemonException {
    public NoTasksException(String message) {
        super(":( OPPS!!! There are no tasks to show in the list!");
    }
}
