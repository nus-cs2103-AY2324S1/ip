package james;

/**
 * Represents a ToDoTask.
 */
public class ToDoTask extends Task {

    /**
     * Constructor for ToDoTask.
     * @param description Description of the ToDoTask.
     */
    public ToDoTask(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the ToDoTask.
     * @return String representation of the ToDoTask.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
