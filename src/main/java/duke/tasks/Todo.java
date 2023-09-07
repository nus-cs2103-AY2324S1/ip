package duke.tasks;

/**
 * Represents a Todo object that is a Task.
 */
public class Todo extends Task {
    /**
     * Creates a Todo object.
     *
     * @param description The description of the Todo
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "|T| " + super.toString();
    }
}
