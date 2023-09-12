package bee;

/**
 * Represents a todo task that needs to be done.
 * Inherits from the parent class Task.
 */
public class Todo extends Task {

    /**
     * Constructs a new todo task with the given description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
        assert description != null : "Description cannot be null."; // Assumption: Description should not be null
    }

    /**
     * Constructs a new todo task with the given description and completion status.
     *
     * @param description The description of the todo task.
     * @param isDone      The completion status of the todo task.
     */
    public Todo(String description, Boolean isDone) {
        super(description, isDone);
        assert description != null : "Description cannot be null."; // Assumption: Description should not be null
    }

    /**
     * Returns a string representation of the todo task.
     * Includes the task type indicator "[T]".
     *
     * @return A formatted string representing the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
