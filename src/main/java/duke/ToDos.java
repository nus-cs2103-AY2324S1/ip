package duke;

/**
 * Represents a ToDos task. A <code>ToDos</code> corresponds to a Task
 * which has a description, and stores information whether this task
 * has been completed.
 */
public class ToDos extends Task {
    /**
     * Constructs a new instance of a ToDos task.
     *
     * @param description Name of the ToDos task.
     */
    public ToDos(String description) {
        super(description);
    }

    /**
     * Constructs a new instance of a ToDos task.
     *
     * @param description Name of the ToDos task.
     * @param isDone {@code true} if the task is marked as done, {@code false} otherwise.
     */
    public ToDos(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toFileString() {
        return "T" + super.toFileString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}