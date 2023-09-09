package duke;

/**
 * Represents a task that the user needs to do.
 *
 * @author Qin Yan Er
 */
public class Todo extends Task {

    /**
     * Creates a new Todo instance.
     *
     * @param description contains the description of the todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a formatted string to be saved into a file.
     *
     * @return A formatted string representing the task.
     */
    @Override
    public String saveTask() {
        return "T" + " | " + super.saveTask();
    }

    /**
     * Returns a formatted string for display.
     *
     * @return A formatted string representing the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
