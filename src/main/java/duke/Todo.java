package duke;

/**
 * Categorise task as a todo
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    /**
     * Returns string representation of the todo task in the
     * format that will be outputted to the user.
     *
     * @return String representation of todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
