package arona.task;
/**
 * Represents a to-do task. A to-do task is a simple task with a description.
 */
public class ToDoTask extends Task {
    /**
     * Constructs a new to-do task with the given description.
     *
     * @param description The description of the to-do task.
     */
    public ToDoTask(String description) {
        super(description);
    }

    /**
     * Constructs a new to-do task with the given description and marked status.
     *
     * @param description The description of the to-do task.
     * @param isMarked    The marked status of the task (1 for marked, 0 for unmarked).
     */
    public ToDoTask(String description, int isMarked) {
        super(description, isMarked);
    }

    /**
     * Returns a string representation of the to-do task, including its status icon.
     *
     * @return A string representation of the to-do task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

