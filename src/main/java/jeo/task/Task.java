package jeo.task;

/**
 * Represents a task used for the Je-O chatbot.
 *
 * @author Joseph Oliver Lim
 */
public abstract class Task implements Comparable<Task> {
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
    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done yet.
     */
    public void unmarkAsDone() {
        isDone = false;
    }

    /**
     * Checks whether the task description contains a certain keyword.
     *
     * @param keyword The keyword used to match the task description.
     * @return A boolean indicating whether the task description contains the keyword.
     */
    public boolean contains(String keyword) {
        return description.contains(keyword);
    }

    /**
     * Returns the String representation of the Task.
     *
     * @return String representation of the Task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}
