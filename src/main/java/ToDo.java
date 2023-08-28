/**
 * The ToDo class represents a task without a specific due date or time.
 * It inherits from the Task class and adds a specific task type indicator.
 */
public class ToDo extends Task {
    /**
     * Constructs a new ToDo object with the specified description.
     *
     * @param description The description of the ToDo task.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns a string representation of the ToDo task.
     *
     * @return A formatted string indicating the task type, completion status, and description.
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

    @Override
    public String writeFile() {
        return "T | " + super.writeFile();
    }
}
