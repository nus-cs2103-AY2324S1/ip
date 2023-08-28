package taskclasses;

/**
 * Represents a task of type "Todo" that can be completed.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo task with the given description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }
    @Override
    public String formatToFile() {
        return "T | " + super.formatToFile();
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
