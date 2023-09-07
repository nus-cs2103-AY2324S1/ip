package duke.Utils;

/**
 * The TaskNotFoundException class represents an exception that is thrown when
 * a user provides an input number that is out of range of the current tasks.
 */
public class TaskNotFoundException extends DukeException {
    /**
     * Constructs a new TaskNotFoundException with a default error message.
     */
    protected TaskNotFoundException() {
        super("I'm sorry, but your input number is out of range of the current tasks");
    }
}