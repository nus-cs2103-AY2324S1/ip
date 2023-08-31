package duke;

/**
 * Represents a Todo task in the Duke application.
 * A Todo task is a simple task with just a description and no specific time associated with it.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo task with the given description.
     *
     * @param description The description of the Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the Todo task.
     * The representation includes a "[T]" prefix to indicate that it's a Todo task.
     *
     * @return The string representation of the Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the string representation of the Todo task for file storage.
     * The representation is in a format suitable for saving to a file.
     *
     * @return The string representation of the Todo task for file storage.
     */
    @Override
    public String toFile() {
        return "T | " + super.toFile();
    }
}
