package duke.task;

/**
 * Represents a todo task.
 * Inherits from the Task class.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo object with the given description.
     *
     * @param description The description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the ToDo object.
     *
     * @return A string representation of the ToDo object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a formatted string representation of the ToDo object.
     *
     * @return A formatted string representation of the ToDo object.
     */
    @Override
    public String toFormattedString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
