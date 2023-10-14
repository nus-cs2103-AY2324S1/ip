package shiba.exceptions;

/**
 * Represents an exception that occurs when an operation is performed on an empty list of tasks.
 */
public class EmptyTasksException extends ShibaException {
    public EmptyTasksException() {
        super("You don't have any tasks yet!");
    }
}
