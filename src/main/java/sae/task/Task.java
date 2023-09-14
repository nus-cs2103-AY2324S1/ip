package sae.task;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The sae.task.Task class represents a sae.task with a description and a status indicating whether it's done or not.
 */
abstract public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new sae.task with the given description. By default, the sae.task is marked as not done.
     *
     * @param description The description of the sae.task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the sae.task. The icon is "X" if the sae.task is done, or a space if it's not done.
     *
     * @return The status icon of the sae.task.
     */
    public String getStatus() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the description of the sae.task.
     *
     * @return The description of the sae.task.
     */
    public String toString() {
        return "[" + this.getStatus() + "] " + this.description;
    }

    /**
     * Marks the sae.task as done by setting its status to true.
     */
    public void markTask() {
        this.isDone = true;
    }

    public void unMarkTask() {
        this.isDone = false;
    }

    public abstract String toFileString();

}
