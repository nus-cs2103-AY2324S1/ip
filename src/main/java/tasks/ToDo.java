package tasks;

/**
 * Represents a ToDo task.
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }
    public ToDo(String description, TaskList list) {
        super(description, list);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
