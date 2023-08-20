/**
 * Represents a ToDo task.
 */
public class ToDo extends Task {
    /**
     * Creates a ToDo object.
     * @param description Description of the task.
     */
    public ToDo(String description) {
        super(description);
    }
    /**
     * Returns the string representation of the ToDo task.
     * @return string representation of the ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
