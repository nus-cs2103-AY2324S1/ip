package duke.task;

/**
 * Represents a todo task.
 * Inherits properties and methods from the Task class.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo object with the given description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the Todo task, including its type, completion status, and description.
     *
     * @return A string representation of the Todo task.
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
