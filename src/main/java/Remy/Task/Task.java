package remy.task;

import java.io.Serializable;

/**
 * A Task that can be added into the Tasklist.
 */
public class Task implements Serializable {
    protected String description;
    protected boolean isDone;
    protected String priority;

    /**
     * Creates a Task object with a description.
     *
     * @param description Name of Task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.priority = null;
    }

    /**
     * Creates a Task object with a description and a priority.
     *
     * @param description Name of Task
     * @param priority    Priority of Task. Can be high, medium, or low.
     */
    public Task(String description, String priority) {
        this.description = description;
        this.isDone = false;
        this.priority = priority;
    }

    /**
     * Returns String [ ] if task is not done, and [X] if it is done.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Returns the description (usually name) of a task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks Task as Done. Status icon: [X].
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks Task as Undone. Status icon: [ ].
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns String representation of Task, including Status icon and Task name.
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + this.description + this.getPriorityString();
    }

    /**
     * Returns a String representation of the Task's priority.
     */
    public String getPriorityString() {
        return this.priority == null ? "" : " (priority: " + this.priority + ")";
    }
}
