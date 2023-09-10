package buddy;

/**
 * Represents a todo task.
 */
public class Todo extends Task {

    /**
     * The constructor for a Todo task.
     * @param description The description of the todo task
     * @param isDone The status of the todo task
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String getTaskType() {
        return "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
