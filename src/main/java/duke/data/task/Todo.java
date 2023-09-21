package duke.data.task;

/**
 * Represents a simple task in the todo list.
 */
public class Todo extends Task {
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
