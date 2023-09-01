package simon.task;

/**
 * The {@code ToDo} class extends the {@code Task} class and represents a basic task
 * with no specific start or end time.
 */
public class ToDo extends Task {

    /**
     * Constructs a new ToDo task with the given name.
     *
     * @param taskName The name or description of the task.
     */
    public ToDo(String taskName) {
        super(taskName);
    }

    /**
     * Converts this ToDo task to a string format suitable for display.
     *
     * @return A string representation of this ToDo task.
     */
    @Override
    public String toString() {
        return " [T]" + (super.isDone ? "[X] " : "[ ] ") + super.toString();
    }
}
