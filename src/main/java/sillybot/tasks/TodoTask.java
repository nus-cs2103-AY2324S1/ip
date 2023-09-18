package sillybot.tasks;

/**
 * Represents a TodoTask object that is a Task.
 */
public class TodoTask extends Task {
    /**
     * Creates a TodoTask object.
     *
     * @param description The description of the TodoTask
     */
    public TodoTask(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
