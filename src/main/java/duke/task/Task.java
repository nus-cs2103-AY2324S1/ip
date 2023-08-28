package duke.task;

/**
 * Represents a task used for the Duke application.
 *
 * @author Joseph Oliver Lim
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a task with a specified description.
     *
     * @param description A string describing the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done yet.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * Checks whether the task description contains a certain keyword.
     *
     * @param keyword The keyword used to match the task description.
     * @return A boolean indicating whether the task description contains the keyword.
     */
    public boolean contains(String keyword) {
        return this.description.contains(keyword);
    }

    /**
     * Returns the String representation of the Task.
     *
     * @return String representation of the Task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
