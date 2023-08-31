package ekud.state;

/**
 * Represents a simple task to be completed.
 */
public class TodoTask extends Task {
    /**
     * Creates a new simple task.
     * 
     * @param title  The title of the task.
     * @param isDone Whether the task has been completed.
     */
    public TodoTask(String title, boolean isDone) {
        super(title, isDone);
    }

    /**
     * Returns the string representation of this task, to be displayed to the user.
     * 
     * @return The string representation.
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
