/**
 * Represents an EmptyTaskListException.
 *
 * @author Rayson
 */
public class EmptyTaskListException extends Exception {

    public EmptyTaskListException() {
        super("☹ OOPS!!! The task list cannot be empty.");
    }
}
