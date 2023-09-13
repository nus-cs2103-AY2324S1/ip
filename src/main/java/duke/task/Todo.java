package duke.task;

/**
 * Represents a Todo task.
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo object.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the Todo task.
     *
     * @return String representation of the Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the string representation of the Todo task to be saved in the file.
     *
     * @return String representation of the Todo task to be saved in the file.
     */
    @Override
    public String toFileString() {
        return "T" + super.toFileString();
    }
}
