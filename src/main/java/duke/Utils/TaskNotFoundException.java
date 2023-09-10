package duke.utils;

/**
 * The TaskNotFoundException class represents an exception that is thrown when
 * a user provides a search keyword that does not match any of the current tasks.
 */
public class TaskNotFoundException extends DukeException {
    /**
     * Constructs a new TaskNotFoundException with a default error message.
     */
    protected TaskNotFoundException() {
        super("I'm sorry, but none of the task matches your search keyword");
    }
}
