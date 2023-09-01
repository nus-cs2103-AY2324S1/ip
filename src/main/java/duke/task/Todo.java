package duke.task;

/**
 * Represents a Todo Task.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String getSaveString() {

        return String.format("%d todo %s", isDone ? 1 : 0, description.trim());
    }
}
