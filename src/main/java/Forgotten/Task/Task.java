package Forgotten.Task;

import Forgotten.Priority;

/**
 * Encapsulates the tasks.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected Priority priority;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.priority = Priority.LOW;
    }

    /**
     * This method returns the status of this task.
     *
     * @return A string "X" if it is done otherwise an empty string.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * This method sets the status of this task to be done.
     */
    public void setIsDone() {
        isDone = true;
    }

    /**
     * This method sets the status of this task to be not done.
     */
    public void setNotDone() {
        isDone = false;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return this.description;
    }

    public String toFileString() {
        return "[" + this.getStatusIcon() + "]" + " " + this.description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + " " + this.description;
    }
}