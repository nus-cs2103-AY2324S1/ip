package duke.task;

/**
 * Represents a todo task with a description.
 * Todo tasks are tasks that need to be done without a specific time frame.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo object with the given description.
     *
     * @param description Description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the todo task.
     *
     * @return Formatted string showing the todo's type, completion status, and description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Serializes the todo task to a string format for saving.
     *
     * @return Serialized string representation of the todo task.
     */
    @Override
    public String serialize() {
        return String.format("T | %d | %s", isDone ? 1 : 0, description);
    }
}
