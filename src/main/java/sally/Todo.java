package sally;

/**
 * Represents a task that needs to be done.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo object with the specified task description.
     *
     * @param task The description of the task.
     */
    public Todo(String task) {
        super(task);
    }

    /**
     * Constructs a Todo object with the specified task description and priority.
     *
     * @param task The description of the task.
     * @param priority The priority of the task.
     */
    public Todo (String task, String priority) {
        super(task, priority);
    }

    /**
     * Returns a string representation of the Todo task.
     * The string includes the task status icon and description.
     *
     * @return The formatted string representation of the Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
