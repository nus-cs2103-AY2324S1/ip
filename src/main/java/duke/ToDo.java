package duke;

/**
 * Represents a todo task that can be added to the task list.
 */
public class ToDo extends Task {

    /**
     * Constructs a todo task with the specified description and status.
     *
     * @param description The description of the todo task.
     * @param isDone      Indicates whether the task is done or not.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns a string representation of the todo task.
     *
     * @return A string containing the status icon and description of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a formatted string for saving the todo task.
     *
     * @return A string containing the task type, status, and description.
     */
    @Override
    public String toSaveString() {
        return "T," + isDone + "," + description;
    }
}
