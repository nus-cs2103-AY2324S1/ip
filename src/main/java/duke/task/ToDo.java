package duke.task;

/**
 * Represents a task without any specific due date or time.
 */
public class ToDo extends Task {
    /**
     * Constructs a ToDo object.
     *
     * @param name The name or description of the task.
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Converts the ToDo object to its string representation.
     *
     * @return The string representation of the ToDo object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Converts the ToDo object to a formatted string for storage.
     *
     * @return The formatted string for storage.
     */
    public String convertTaskToStorageFormat() {
        return "T | " + (super.getIsDone() ? "1" : "0") + " | " + super.getName();
    }
}
