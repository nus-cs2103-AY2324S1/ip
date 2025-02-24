package duke.task;

/**
 * Represents a to-do task in the Duke application.
 * It is a subclass of the Task class.
 */
public class ToDo extends Task {
    /**
     *  * Constructor for creating a ToDo task.
     *
     * @param description The description of the task.
     */
    public ToDo(String description) {
        super(description);
    }
    /**
     * Converts the ToDo task to a string format suitable for saving to a text file.
     *
     * @return A string representation of the task in a format that can be saved to a text file.
     */
    @Override
    public String toTxtString() {
        return "[T] | [" + (this.isDone ? "X" : " ") + "] | " + this.description;

    }
    /**
     * Converts the ToDo task to a string format for display.
     *
     * @return A string representation of the task for display purposes.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
