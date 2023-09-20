package duke;

/**
 * Represents a to-do task with a description but no specific date and time associated with it.
 * Extends the Task class.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo task with a description.
     *
     * @param description The description of the ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the ToDo task, including its status icon and description.
     *
     * @return A formatted string representing the ToDo task.
     */
    @Override
    public String toString() {
        String status = "[" + getStatusIcon() + "] ";
        return " [T]" + status + description;
    }
}

