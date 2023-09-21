package duke;

/**
 * Encapsulates tasks for the chatbot.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with specified description.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        assert !description.isEmpty();

        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a Task with specified description and whether
     * it is completed.
     */
    public Task(String description, boolean isDone) {
        assert !description.isEmpty();

        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Gets the status Icon of the Task.
     */
    public String getStatusIcon() {
        // X for done task
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns a String representation for the task for output.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns a String representation of the task for storage.
     */
    public String toTxt() {
        return (this.isDone ? "1" : "0") + " | " + this.description;
    }

}
