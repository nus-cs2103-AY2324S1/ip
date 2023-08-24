/**
 * Custom Exception class that extends DukeException. It is thrown when the user input references an
 * invalid index in the Task List.
 *
 */
public class InvalidIndexException extends DukeException{
    /**
     * Constructs new InvalidIndexException with specified error messages.
     *
     */
    InvalidIndexException() {
        super("Index given out of task list range!");
    }
}
