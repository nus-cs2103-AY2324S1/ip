package duke.exception;

public class TaskNotFoundException extends Exception {
    /**
     * Constructs an TaskNotFoundException object which happens when user tries to access a task
     * that does not exist.
     */
    public TaskNotFoundException() {
        super("Task not found!");
    }
}
