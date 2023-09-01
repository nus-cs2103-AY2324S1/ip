package duke.task;

/**
 * Represent tasks of type todo.
 *
 * @author Armando Jovan Kusuma
 */
public class Todo extends Task {

    /**
     * Creates a todo task with the specified description.
     *
     * @param description the description of the task todo.
     */
    public Todo (String description) {
        super(description);
    }

    /**
     * Returns the string representation of the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
