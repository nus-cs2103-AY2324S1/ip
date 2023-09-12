package duke;

import java.io.Serializable;

/**
 * Class representing a task to be completed.
 */
class Task implements Serializable {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for the Task object.
     * @param description The description of what the task is about
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Task's completion status.
     * @return "1" if the task is completed and "0" otherwise
     */
    public int getStatusIcon() {
        return (isDone ? 1 : 0);
    }

    /**
     * Mark the task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Unmark a task as not done yet.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     * @return Description of the task in a String
     */
    public String desc() {
        return this.description;
    }
}