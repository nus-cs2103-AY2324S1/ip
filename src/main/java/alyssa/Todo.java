/**
 * A Todo is a task without a date/time attached to it.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
