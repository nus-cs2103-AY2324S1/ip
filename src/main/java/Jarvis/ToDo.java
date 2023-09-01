package Jarvis;

/**
 * Represents a ToDo task that extends the Task class.
 * <p>
 *     The ToDo class is a subclass of the Task class
 * </p>
 */
public class ToDo extends Task {

    /**
     * Constructs a new ToDo object.
     *
     * @param description description of the deadline task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Converts the task to a user-friendly string representation
     *
     * @return A string representation of the task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
