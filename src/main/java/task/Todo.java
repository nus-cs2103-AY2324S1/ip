package task;

/**
 * Todo is a Task that has only the description.
 */
public class Todo extends Task {

    /**
     * The constructor of Todo.
     *
     * @param description the todo description.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * The constructor of Todo with specified status.
     *
     * @param description The todo description.
     * @param isDone The status of the todo.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
