package duke.task;

/**
 * Represents a task that has no specific date attached to it.
 */
public class TodoTask extends Task {
    /**
     * Constructs a TodoTask object with the specified description.
     * @param description The description of the todo task.
     */
    public TodoTask(String description) {
        super(description);
    }

    /**
     * Returns a string representation of todo task.
     * @return The method is returning a string representation of a todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
