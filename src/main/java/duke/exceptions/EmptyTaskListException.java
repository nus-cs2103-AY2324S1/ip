package duke.exceptions;

/**
 * Represents an exception that is thrown when the task list is empty.
 */
public class EmptyTaskListException extends Exception {

    /**
     * Constructs an EmptyTaskListException with a default error message.
     */
    public EmptyTaskListException() {
        super("☹ OOPS!!! The task list cannot be empty.");
    }
}
