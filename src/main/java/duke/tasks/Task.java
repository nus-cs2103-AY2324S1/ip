package duke.tasks;

import java.io.Serializable;

/**
 * Represents a task which the user has to do.
 */
public class Task implements Serializable {
    protected String description;
    protected boolean isDone;
    protected Character taskType;

    /**
     * Constructor for tasks with no categories.
     *
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.taskType = '_';
    }

    /**
     * Another constructor for tasks with categories.
     * @param description Description of task.
     * @param taskType Type of task needed to be done.
     */
    public Task(String description, Character taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    /**
     * Getter for status icon of task.
     *
     * @return Status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Getter for description of task.
     *
     * @return Description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter to mark task as not completed.
     */
    public void markNotDone() {
        isDone = false;
    }

    /**
     * Setter to mark task as completed.
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * Getter to get task type.
     * @return task type.
     */
    public Character getTaskType() {
        return this.taskType;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.getDescription());
    }
}
