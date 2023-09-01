package duke;

/**
 * Represents a Todo task, a subclass of Task.
 */
public class Todos extends Task {
    /**
     * Creates a new instance of a ToDo task with the provided description.
     *
     * Creates a new instance of a ToDo task with the provided description.
     */
    public Todos(String description) {
        super(description);
    }

    /**
     * Returns the saving format of the ToDo task.
     *
     * @return The string representation of the ToDo task suitable for saving to a file.
     */
    @Override
    public String getSavingFormat() {
        return "[T] | [" + getStatusIcon() + "] | " + description;
    }

    /**
     * Returns a string representation of the ToDo task.
     *
     * @return The string representation of the ToDo task in the format: [T][status icon] description.
     */
    @Override
    public String toString() {
        return "[T][" + getStatusIcon() + "] " + description;
    }
}
