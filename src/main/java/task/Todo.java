package task;

/**
 * Represents a To-Do task.
 */
public class Todo extends Task {

    /**
     * Creates a new instance of a To-Do task with the specified description.
     *
     * @param description The description or name of the To-Do task.
     */
    public Todo(String description) {
        super(TaskType.TODO.toString(), description);
    }

    /**
     * Returns a string representation of the To-Do task, including its type and description.
     *
     * @return A string representation of the To-Do task.
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

    /**
     * Converts the To-Do task to a string representation suitable for saving to a file.
     *
     * @return A formatted string representing the To-Do task for file storage.
     */
    @Override
    public String toFileString() {
        return TaskType.TODO.toString() + " | " + (isCompleted() ? "1" : "0") + " | " + getDescription();
    }
}
