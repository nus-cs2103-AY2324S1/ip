package extensions.tasks;

/**
 * A task.
 */
public class Task {

    // The description of the task.
    protected final String description;

    // The status of the task. True if done, false if not done.
    protected boolean isDone = false;

    /**
     * Constructor for a Task.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks this task as undone.
     */
    public void unmarkAsDone() {
        isDone = false;
    }

    /**
     * Returns the status of the checkbox for the task.
     *
     * @return String [ ] if not done, [X] if done.
     */
    private String getStatusCheckbox() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Returns the String representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return getStatusCheckbox() + " " + description;
    }

    /**
     * Exports the task to a String to be saved.
     *
     * @return String representation of the task to be saved.
     */
    public String export() {
        return String.format("%s || %s",
                isDone ? "X" : " ",
                description);
    }
}
