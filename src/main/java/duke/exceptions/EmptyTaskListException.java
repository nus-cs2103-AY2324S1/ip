package duke.exceptions;

/**
 * Represents an Duke.Exceptions.EmptyTaskListException.
 *
 * @author Rayson
 */
public class EmptyTaskListException extends Exception {

    public EmptyTaskListException() {
        super("☹ OOPS!!! The task list cannot be empty.");
    }
}
