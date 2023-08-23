/**
 * Represents a task without any date/time attached to it.
 * This class is a subclass of the Task class and inherits its properties and methods.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo object with the given description.
     *
     * @param description The description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the ToDo task.
     *
     * @return The string representation of the ToDo task with description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
