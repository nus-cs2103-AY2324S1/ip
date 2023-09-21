package duke.exception;

/**
 * Represents an error that occurred during the initialisation of a Deadline object.
 */
public class ToDoException extends CommandException {

    public ToDoException() {
        super("todo (description)");
    }
}
