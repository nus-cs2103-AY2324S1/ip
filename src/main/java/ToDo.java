/**
 * This class is a subclass of Task which defines a todo task.
 */
public class ToDo extends Task {
    /**
     * A constructor for todo task object.
     * @param description Name of todo task
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     *
     * @return String representation of todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
