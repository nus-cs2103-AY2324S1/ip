package duke.task;

/**
 * Represents a Todo.
 */
public class Todo extends Task {

    /**
     * Constructor for Todo.
     *
     * @param description String describing the Todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the Todo.
     *
     * @return string representation of the Todo task.
     */
    @Override
    public String toString() {
        String msg = "[T]" + super.toString();
        return msg;
    }
}
