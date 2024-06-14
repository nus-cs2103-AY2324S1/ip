package duke.task;

/**
 * Represents a todo task.
 */
public class Todo extends Task {
    private static final String SYMBOL = "T";

    /**
     * Constructor for Todo.
     *
     * @param description Description of the todo task.
     */
    public Todo(String description) {
        this(description, false);
    }

    /**
     * Constructor for Todo.
     *
     * @param description Description of the todo task.
     * @param isCompleted Whether the todo task is completed.
     */
    public Todo(String description, boolean isCompleted) {
        super(description, isCompleted);
    }

    @Override
    public String getDataString() {
        return String.join(" | ", Todo.SYMBOL, super.isCompleted ? "1" : "0", super.getDescription());
    }

    @Override
    public String toString() {
        return String.format("[%s]%s", Todo.SYMBOL, super.toString());
    }
}
