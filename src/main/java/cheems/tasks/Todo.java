package cheems.tasks;

/**
 * A child class of the abstract Task class.
 * Represents a task that is a to-do.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    /**
     * Formats the todo task in a user-friendly readable string.
     * @return A string representation of the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toStorage() {
        return String.format("%d|TODO|%s", this.isDone ? 1 : 0, this.getDescription());
    }
}
