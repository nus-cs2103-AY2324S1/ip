package bob.task;

/**
 * A Todo is a task that can be done in the future, but has no specific deadline or period
 * of which it should be completed.
 */
public class Todo extends Task {

    /**
     * Constructor of Todo Class.
     *
     * @param description Test description of Todo Task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructor of Todo Class.
     * Instantiates an instance of Todo class with a string description and
     * a boolean indicating whether the task is done.
     *
     * @param description Text description of Todo Task
     * @param isDone Completion status of Todo Task
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String convertToFileFormat() {
        return String.format("T|%s", super.convertToFileFormat());
    }
}
