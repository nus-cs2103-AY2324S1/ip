package alyssa;
/**
 * A Todo is a task without a date/time attached to it.
 */
public class Todo extends Task {
    /**
     * Constructor method for Todo.
     * @param description Description of the Todo.
     */
    public Todo(String description) {
        super(description);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
