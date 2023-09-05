package task;

/**
 * Todo class is used for tasks that need to done.
 */
public class Todo extends Task{

    /**
     * Instantiates a new Todo.
     *
     * @param description the description
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getStatusIcon() {
        return "[T]" + super.getStatusIcon();
    }

    @Override
    public String toString() {
        return String.format("T | %d | %s", super.isDone ? 1 : 0, super.description);
    }
}
