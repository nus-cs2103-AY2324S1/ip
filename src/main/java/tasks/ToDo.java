package tasks;

/**
 * The ToDo class represents a task that has no specific due date or time.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo instance with the specified description
     * and sets the completion status to false.
     *
     * @param des The description of the ToDo task.
     */
    public ToDo(String des) {
        super(des);
    }

    /**
     * Constructs a ToDo instance with the specified description and completion status.
     *
     * @param des  The description of the ToDo task.
     * @param mark The completion status of the ToDo task.
     */
    public ToDo(String des, boolean mark) {
        super(des, mark);
    }

    /**
     * Returns the string representation of the ToDo task.
     *
     * @return The formatted string representation of the ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
