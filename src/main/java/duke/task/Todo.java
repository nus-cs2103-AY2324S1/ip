package duke.task;

/**
 * Represents a todo task.
 */
public class Todo extends Task {
    private static String SYMBOL = "T";
    
    /**
     * Constructor for Todo.
     * 
     * @param description Description of the todo task.
     */
    public Todo (String description) {
        super(description);
    }

    @Override
    public String getDataString() {
        return String.join(" | ", Todo.SYMBOL, super.isDone ? "1" : "0", super.getDescription());
    }

    @Override
    public String toString() {
        return String.format("[%s]%s", Todo.SYMBOL, super.toString());
    }
}
