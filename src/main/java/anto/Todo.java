package anto;

/**
 * Todo class represents a Todo task.
 */
public class Todo extends Task {
    /**
     * Creates a Todo task
     * @param description Description of todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Return string for writing to storage.
     *
     * @return Task in storage format.
     */
    public String getStoreFormat() {
        assert this.description != null;
        return String.format("\nT | %s | %s", this.isDone ? "1" : "0", this.description);
    }

    /**
     * Overridden toString to represent a Todo task.
     *
     * @return String representing a Todo task.
     */
    @Override
    public String toString() {
        assert this.getStatusIcon() != null;
        return String.format("[T] [%s] %s",
                this.getStatusIcon(),
                super.toString());
    }
}
