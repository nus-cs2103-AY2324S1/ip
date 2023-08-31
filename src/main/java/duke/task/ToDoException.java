package duke.task;

/**
 * Represents an error that occurred during the initialisation of a Deadline object.
 */
public class ToDoException extends TaskException {

    public ToDoException() {
        super("todo (description)");
    }
}