package duke.exception;

/**
 * Represents an exception that occurs when the format for a command is incorrect.
 */
public class TodoException extends DukeException {

    /**
     * Constructs a TodoException with a specified detail message.
     */
    public TodoException() {
        super("The description of a todo cannot be empty.");
    }
}
