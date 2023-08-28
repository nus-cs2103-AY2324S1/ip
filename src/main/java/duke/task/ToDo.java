package duke.task;

import duke.task.Task;

public class ToDo extends Task {
    /**
     * Constructor to build a task with description as input.
     * @param description Describes the task.
     */
    public ToDo(String description) {
        this(description, false);
    }

    /**
     * Constructor to build a task with description and isDone status.
     * @param description Describes the task.
     * @param isDone Status of whether task is completed.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Prints out the description of the task and its status.
     * @return A string that shows the task's description and status.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}