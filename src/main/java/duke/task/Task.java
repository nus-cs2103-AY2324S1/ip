package duke.task;

/**
 * Encapsulates a task that can be added to the task manager.
 *
 * @author Teo Kai Sheng
 */
public class Task {
    /**
     * Description of the task.
     */
    protected String description;

    /**
     * Completion status of the task.
     */
    protected boolean isDone;

    /**
     * Constructor to create a task.
     * @param description The desciption of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the String representation of the status icon of the task.
     * @return The status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Changes the completion status of the task to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Changes the completion status of the task to false.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns the String representation of the task.
     * @return A String representing the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] " + this.description, this.getStatusIcon());
    }

    /**
     * Returns the String representation of the task to be saved in the hard disk.
     * @return A String representing the task to be saved in the hard disk.
     */
    public String taskToString() {
        return String.format("%s | " + this.description, (isDone ? "Y" : "N"));
    }
}
