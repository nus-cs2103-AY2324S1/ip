package duke.task;

import duke.Task;

/**
 * Represents a to-do task with a description.
 * A Todo task contains a description of the task to be done.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo task with the specified description.
     *
     * @param description The description of the Todo task.
     */
    public Todo(String description) {
        super(description, Type.TODO);
    }

    /**
     * Returns a string representation of the Todo task.
     * The string includes the task type, status icon, and description.
     *
     * @return A formatted string representing the Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + "[" + getStatusIcon() + "]" + " " + description;
    }

    /**
     * Converts the Todo task to a formatted string for storage in a file.
     *
     * @return A string representing the Event task in the format suitable for storage.
     */
    public String toFileString() {
        String statusIcon = (isDone ? "1" : "0");
        return "T " + "| " + statusIcon + " | " + getDescription() + "\n";
    }
}
