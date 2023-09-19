package duke.task;

/**
 * Represents a Todo task.
 */
public class Todo extends Task {

    /**
     * Constructor of the  task
     * @param description Description of the  task
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
