package duke;

/**
 * Represents a todo task without any specific deadline or timeframe.
 * Inherits from the Task class.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo object with the provided description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Converts the Todo object to a string representation.
     *
     * @return The formatted string representation of the Todo object.
     */
    @Override
    public String toString() {
        return "[T]" + "[" + this.getStatusIcon() + "]" + super.toString();
    }
}
