package duke.Exceptions;

/**
 * Custom exception class for empty todo entries in the Duke application.
 * This exception is thrown when a user attempts to add a todo task without a title.
 */
public class EmptyTodoException extends DukeException {

    /**
     * Constructs a new EmptyTodoException with a predefined error message.
     * The error message informs the user about the correct format for adding a todo.
     */
    public EmptyTodoException() {
        super("I DON'T LIKE WHAT YOU'VE GOT! Missing title of todo. ENTER todo (title) to add a todo");
    }
}
