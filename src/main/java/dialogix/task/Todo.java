package dialogix.task;

/**
 * Represents a to-do task that extends the Task class.
 */
public class Todo extends Task {

    /**
     * Creates a new Todo task with the given description.
     *
     * @param description The description of the to-do task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a formatted string for saving the task to a file.
     *
     * @return A formatted string representing the task for file storage.
     */
    @Override
    public String getOutputFormat() {
        return String.format("T | %d | %s", isDone() ? 1 : 0, getDescription());
    }

    /**
     * Returns a string representation of the to-do task.
     *
     * @return A string containing the task type and description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
