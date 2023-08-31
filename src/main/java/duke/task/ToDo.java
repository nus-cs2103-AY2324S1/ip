package duke.task;

/**
 * Represents a task with a description.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo object with the task description and deadline.
     *
     * @param description Description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Formats the string representation of the ToDo object to write to the file.
     *
     * @return String representation of the ToDo object to be written to the file.
     */
    @Override
    public String toFileString() {
        String type = "T";
        return type + " | " + (getIsDone() ? "1" : "0") + " | " + getDescription();
    }

    /**
     * The string representation of ToDo object.
     *
     * @return String representation of the ToDo object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}