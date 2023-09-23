package tasks;

/**
 * Represents a Todo task, which extends the Task class.
 * A Todo task is characterized by its description.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo object with a given description.
     *
     * @param description The description.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the Todo task,
     * which includes the task type identifier "[T]" and its description.
     *
     * @return The string representation of the Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
