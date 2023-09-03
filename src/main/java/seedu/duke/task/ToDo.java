package seedu.duke.task;

/**
 * Represents a ToDo task.
 *
 * @author Win Sheng
 * @since 3 September 2023
 */
public class ToDo extends Task {

    /**
     * Constructs a new Deadline task with the specified description.
     *
     * @param description
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Gets the type of the ToDo task.
     *
     * @return The type "[T]" for the ToDo task.
     */
    @Override
    public String type() {
        return "[T]";
    }

    /**
     * Converts the ToDo task to a string format for storing in a file.
     *
     * @return A string representation of the ToDo task for file storage.
     */
    @Override
    public String toFileString() {
        return type() + " | " + (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Returns a string representation of the ToDo task.
     *
     * @return A string representation of the ToDo task.
     */
    @Override
    public String toString() {
        return type() + super.toString();
    }
}
