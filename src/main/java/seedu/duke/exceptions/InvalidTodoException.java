package seedu.duke.exceptions;

/**
 * The InvalidTodoException is thrown when a command that creates a Todo
 * task is not in the correct format.
 */
public class InvalidTodoException extends LemonException {
    public InvalidTodoException(String message) {
        super(":( OPPS!!! The description of a todo cannot be empty.");
    }
}
