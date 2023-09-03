package kiera.task;

/**
 * Type of task.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    /**
     * @inheritDoc
     */
    public String toStorageString() {
        return "T // " + this.getStatusIcon() + " // " + this.getDescription();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        return "[T]" + "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
