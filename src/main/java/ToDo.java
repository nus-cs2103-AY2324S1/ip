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

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toFileString() {
        String status = getStatusIcon().equals("X") ? "1" : "0";
        return "T" + " | " + status + " | " + super.description + " | ";
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
