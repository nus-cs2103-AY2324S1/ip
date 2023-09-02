package fishron;

/**
 * Represents a ToDo task.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo task with the given description.
     *
     * @param description The description of the ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Converts the ToDo task to a string format suitable for saving to a file.
     *
     * @return A string representation of the tToDo task for file storage.
     */
    @Override
    public String toFileString() {
        return "T | " + super.toFileString();
    }

    /**
     * Converts the ToDo task to a user-friendly string format for display.
     *
     * @return A user-friendly string representation of the ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
