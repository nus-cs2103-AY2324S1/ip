package duke.task;

/**
 * Represents a todo task.
 */
public class Todo extends Task{

    /**
     * Constructs a Todo instance with the given description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the Todo task.
     *
     * @return A string containing the task type indicator and description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
