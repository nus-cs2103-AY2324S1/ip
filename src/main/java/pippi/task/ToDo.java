package pippi.task;

/**
 * The ToDo class represents a specific type of task
 * that doesn't include anything aside from task description
 *
 * @author Nathan
 */
public class ToDo extends Task {
    /**
     * Constructs a ToDo instance with the specified ToDo description.
     *
     * @param description The description of the ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the String representation of a ToDo task to the UI
     * @return ToDo string representation
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the String representation of a ToDo task written to the memory
     * @return ToDo string representation
     */
    @Override
    public String toMemory() {
        return "T " + super.getStatus() + super.getDescription();
    }
}
