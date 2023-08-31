package duke;

import java.io.Serializable;

/**
 * Represents a task with a description and completion status.
 */
public class Task implements Serializable {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task object with the provided description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }
    public void mark() {
        this.isDone = true;
    }
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Retrieves the status icon of the task.
     *
     * @return The status icon, "X" if done, " " (space) if not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Converts the Task object to a string representation of its description.
     *
     * @return The description of the task.
     */
    @Override
    public String toString() {
        return this.getDescription();
    }

    public boolean contains(String word) {
        return this.toString().contains(word);
    }
}