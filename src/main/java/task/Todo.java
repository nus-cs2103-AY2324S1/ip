package task;

/**
 * Represents a to-do task.
 *
 * @author Ho Khee Wei
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task with the specified description.
     *
     * @param description The description of the to-do task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the Todo task.
     *
     * @return A string in the format: "[T][Status] Description"
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
