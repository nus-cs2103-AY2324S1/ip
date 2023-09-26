package tasks;

/**
 * Represents a todo task with a description.
 */
public class ToDo extends Task {

    private String taskIcon = "[T]";

    /**
     * A public constructor to initialize a Todo task
     *
     * @param description  a description of the task
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * A public constructor to initialize a Todo task
     *
     * @param description  a description of the task
     * @param isDone task completion status
     */
    public ToDo(String description, boolean isDone) {
        // Constructor for functions.Load
        super(description, isDone);
    }

    @Override
    public String getTaskAsString() {
        String message = String.format("%s[%s] %s", this.taskIcon, this.getStatusIcon(), this.getDescription());
        return message;
    }
}
