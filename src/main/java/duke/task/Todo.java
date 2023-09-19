package duke.task;

/**
 * Represents a todo task.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the todo task.
     * @return A string representation of the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the todo task that is to be saved by Storage.
     * @return The todo task that is to be saved by Storage.
     */
    @Override
    public String toSaveString() {
        return "T|" + super.toSaveString();
    }
}
