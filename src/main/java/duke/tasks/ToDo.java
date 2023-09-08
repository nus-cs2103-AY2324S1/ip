package duke.tasks;

/**
 * Represents a task with a description.
 */
public class ToDo extends Task {

    /**
     * Creates a new {@code ToDo} instance
     *
     * @param description The description of the to-do.
     * @param isDone      The indication of the event being to-do.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    public ToDo(String description) {
        super(description);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String encode() {
        return String.format("T | %d | %s", this.isDone ? 1 : 0,
                this.description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
