package duke.task;

/**
 * Represents a todo task.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the todo task.
     * example: [T][X] return book
     *
     * @return String representation of the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}