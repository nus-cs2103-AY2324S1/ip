package duke.task;

/**
 * Represents a todo.
 */
public class ToDo extends Task {
    /**
    * Creates a todo with the given description.
    * @param description The description of the todo.
    */
    public ToDo(String description) {
        assert description != null && !description.trim().isEmpty() : "description should not be null";
        super.description = description;
    }

    /**
    * Returns the string representation of the todo.
    * @return The string representation of the todo.
    */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
