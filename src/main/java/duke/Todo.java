package duke;

/**
 * Categorise task as a to-do.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    /**
     * Returns string representation of the to-do task in the
     * format that will be outputted to the user.
     *
     * @return String representation of to-do task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
