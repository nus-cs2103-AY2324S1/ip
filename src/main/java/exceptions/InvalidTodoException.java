package exceptions;

/**
 * A DukeException for invalid event task command.
 */
public class InvalidTodoException extends DukeException {

    /**
     * Constructor, initializes the error message.
     */
    public InvalidTodoException() {
        super("Missing description for todo task.\n"
                + "Format should be: todo <task description>");
    }
}
