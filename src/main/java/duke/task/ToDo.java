package duke.task;

import duke.task.Task;

/**
 * The duke.task.ToDo class represents a duke.task of type "duke.task.ToDo" inherited from the duke.task.Task class.
 * It contains a description and inherits methods for marking the duke.task's completion status.
 */
public class ToDo extends Task {

    /**
     * Constructs a new duke.task.ToDo duke.task with the provided description.
     *
     * @param description The description of the duke.task.ToDo duke.task.
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
     * Returns a string representation of the duke.task.ToDo duke.task, including its completion status and description.
     *
     * @return A formatted string indicating the duke.task type and its completion status.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
