package duke;

/**
 * Represents a Todo task.
 * A Todo task is a task without any date/time attached to it.
 */
public class Todo extends Task {

    /**
     * Creates a new Todo task with the given description.
     *
     * @param description The description of the Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the Todo task.
     *
     * @return A string representing the Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the Todo task for file storage.
     *
     * @return A string suitable for storing the Todo task in a file.
     */
    @Override
    public String toFileString() {
        return "T" + super.toFileString();
    }
}