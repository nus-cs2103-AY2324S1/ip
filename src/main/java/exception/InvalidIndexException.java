package exception;

/**
 * Custom Exception class that extends exception.DukeException. It is thrown when the user input references an
 * invalid index in the task.Task List.
 *
 */
public class InvalidIndexException extends DukeException {
    /**
     * Constructs new exception.InvalidIndexException with specified error messages.
     *
     */
    public InvalidIndexException() {
        super("Index given out of task list range!");
    }
}
