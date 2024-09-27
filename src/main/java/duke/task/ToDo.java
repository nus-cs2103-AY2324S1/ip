package duke.task;

/**
 * Represents a ToDo task.
 * This class inherits from {@link Task} and provides a representation
 * of a simple task without any date/time attached to it.
 */
public class ToDo extends Task {
    /**
     * Initializes a new ToDo task with the given description.
     *
     * @param description The description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the ToDo task,
     * with its type and description.
     *
     * @return A string describing the ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation suitable for file storage.
     * The returned string is in a special format to distinguish
     * the task type, its completion status, and its description.
     *
     * @return A formatted string for file storage.
     */
    @Override
    public String toFileString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
