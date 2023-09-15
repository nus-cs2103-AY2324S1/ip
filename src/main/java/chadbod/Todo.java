package chadbod;

/**
 * Represents a to-do task in the ChadBod application, extending the Task class.
 */
public class Todo extends Task {
    /**
     * Constructor for the Todo class.
     *
     * @param description The description of the to-do task.
     */
    public Todo(String description) {
        super(description);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
    @Override
    public String toFileString() {
        return String.format("T | %d | %s", this.isDone ? 1 : 0, this.description);
    }
}
