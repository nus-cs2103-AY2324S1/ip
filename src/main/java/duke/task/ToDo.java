package duke.task;

/**
 * Represents a task of type todo.
 *
 * @author Joseph Oliver Lim
 */
public class ToDo extends Task {

    /**
     * Constructs a todo with a specified description.
     *
     * @param description A string describing the todo.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the String representation of the ToDo.
     *
     * @return String representation of the ToDo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
