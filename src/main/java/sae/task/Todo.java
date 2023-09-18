package sae.task;

/**
 * The Todo class represents a simple task without any additional details.
 * It extends the Task class and inherits its description field.
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo task with the given description.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Overrides the toString method to format the Todo task's details.
     *
     * @return A formatted string representing the Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Converts the Todo task to a formatted string for file storage.
     *
     * @return A formatted string representing the Todo task for file storage.
     */
    public String toFileString() {
        String completionStatus = isDone ? "1" : "0";
        return String.format("%s | %s | %s", "T", completionStatus, description.trim());
    }
}
