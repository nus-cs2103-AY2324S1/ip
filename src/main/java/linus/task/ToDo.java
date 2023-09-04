package linus.task;

/**
 * Represents a ToDo task.
 */
public class ToDo extends Task {

    protected String type = "linus.task.ToDo";

    /**
     * Constructs a ToDo object with the specified description.
     *
     * @param description The description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the icon representing the type of task.
     *
     * @return The icon representing the type of task.
     */
    @Override
    public String getTaskTypeIcon() {
        return "[T]";
    }

}
