package duke.task;

/**
 * Represents a task that is a ToDo in the Duke application.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo object with the specified description.
     *
     * @param description The description of the deadline task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a formatted string representation of the todo task.
     *
     * @return A formatted string displaying the todo task's details.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Generates a formatted string representation of the todo task for saving.
     *
     * @return A formatted string suitable for saving in a data file.
     */
    @Override
    public String toSaveLine() {
        return String.format("T | %d | %s", isDone ? 1 : 0, description);
    }
}
