package javai;
/**
 * Represents a todo task with a description.
 */
public class Todo extends Task {

    /**
     * Constructs a todo task with a description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
