package dukduk;

/**
 * Represents a task of type "To-Do" with a description and completion status.
 */
public class ToDo extends Task {

    /**
     * Initializes a new To-Do task with the given description.
     *
     * @param description The description of the To-Do task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the To-Do task, including its completion status icon.
     *
     * @return A formatted string representing the To-Do task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the To-Do task in a data-friendly format.
     *
     * @return A formatted string suitable for data storage.
     */
    @Override
    public String toDataString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
