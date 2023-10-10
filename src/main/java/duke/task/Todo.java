package duke.task;

/**
 * Represents a task with just a description.
 */
public class Todo extends Task {
    /**
     * Constructor for a todo task.
     * @param description The description of the task
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toLogString() {
        return String.format("T|%s|%s", (isDone ? "X" : "O"), description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
