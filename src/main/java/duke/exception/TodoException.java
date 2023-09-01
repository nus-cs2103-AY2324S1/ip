package duke.exception;

/**
 * Represents an exception that occurs when the format for a command is incorrect.
 */
public class TodoException extends DukeException {
    public TodoException() {
        super("The description of a todo cannot be empty.");
    }
}
