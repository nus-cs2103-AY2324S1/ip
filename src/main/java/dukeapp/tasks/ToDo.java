package dukeapp.tasks;

/**
 * Represents a task with a description.
 */
public class ToDo extends Task {

    protected ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    protected ToDo(String description) {
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
