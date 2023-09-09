package task;

/**
 * Represents a To-Do task.
 */
public class Todo extends Task {

    /**
     * Creates a To-Do task with the specified description.
     *
     * @param description The description of the To-Do task.
     */
    public Todo(String description) {
        super(TaskType.TODO.toString(), description);
    }

    /**
     * Returns a string representation of the To-Do task.
     *
     * @return A string containing the task type and description.
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

    @Override
    public String toFileString() {
        return TaskType.TODO + " | " + (isCompleted() ? "1" : "0") + " | " + getDescription();
    }
}
