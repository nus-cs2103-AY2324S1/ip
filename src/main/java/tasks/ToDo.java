package tasks;

/**
 * The ToDo class represents a task of type "To-Do" with a name and completion status.
 * It extends the Task class and provides a custom string representation of the task
 * specific to the "To-Do" type.
 */
public class ToDo extends Task {

    /**
     * Constructs a new ToDo task with the specified name and sets its initial
     * completion status to "not done."
     *
     * @param name The name of the "To-Do" task.
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Returns a string representation of the "To-Do" task in the format:
     * [T] taskName
     *
     * @return A string representation of the "To-Do" task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
