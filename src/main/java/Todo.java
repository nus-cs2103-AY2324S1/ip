/**
 * Encapsulates a todo task. Todo tasks only have a description.
 */
public class Todo extends Task {

    /**
     * Constructor for a todo task object.
     *
     * @param description task description of todo task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of a todo task with its status.
     *
     * @return String representation of todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
