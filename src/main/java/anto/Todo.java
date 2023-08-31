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
     * Overridden toString to represent a Todo task.
     *
     * @return String representing a Todo task.
     */
    @Override
    public String toString() {
        return String.format("[T] [%s] %s",
                this.getStatusIcon(),
                super.toString());
    }
}
