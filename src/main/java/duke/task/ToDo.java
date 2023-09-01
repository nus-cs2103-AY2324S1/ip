package duke.task;

/**
 * The ToDo class represents a task of type "ToDo" inherited from the Task class.
 * It contains a description and inherits methods for marking the task's completion status.
 */
public class ToDo extends Task {

    /**
     * Constructs a new ToDo task with the provided description.
     *
     * @param description The description of the ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructs a new ToDo task with the provided description and completion status.
     *
     * @param description The description of the ToDo task.
     * @param isDone      The completion status of the task.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Converts the ToDo task to a formatted string suitable for saving to a file.
     *
     * @return A string representation of the ToDo task in a format compatible with file storage.
     */
    @Override
    public String toFileString() {
        String status = isDone ? "1" : "0"; // Using isDone directly for readability
        return "T | " + status + " | " + description;
    }

    /**
     * Returns a string representation of the ToDo task, including its completion status and description.
     *
     * @return A formatted string indicating the task type and its completion status.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}