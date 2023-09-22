package com.nyanbot.duketasks;

import com.nyanbot.dukeparsers.DukeTimeParser;

/**
 * Encapsulates a DukeTasks.Task class. Contains a description and
 * a flag indicating whether the task instance is done.
 *
 * @author Tan Kerway
 */
public class Task {
    protected DukeTimeParser dukeTimeParser;
    private final String description;
    private boolean isDone;
    private boolean isValid;

    /**
     * Constructor for the DukeTasks.Task class.
     *
     * @param description the description of the task to be instantiated
     */
    public Task(String description) {
        this.dukeTimeParser = new DukeTimeParser();
        this.description = description;
        this.isDone = false;
        this.isValid = true;
    }

    /**
     * Constructor for the DukeTasks.Task class.
     *
     * @param description the description of the task to be instantiated
     * @param isDone whether the task is done
     */
    public Task(String description, boolean isDone) {
        this.dukeTimeParser = new DukeTimeParser();
        this.description = description;
        this.isDone = isDone;
        this.isValid = true;
    }

    /**
     * Constructor for the DukeTasks.Task class.
     *
     * @param description the description of the task to be instantiated
     * @param isDone whether the task is done
     * @param isValid whether the task is valid
     */
    public Task(String description, boolean isDone, boolean isValid) {
        this.dukeTimeParser = new DukeTimeParser();
        this.description = description;
        this.isDone = isDone;
        this.isValid = isValid;
    }

    /**
     * Returns the status icon based on whether the task was completed.
     * If the task has been completed, "X" will be returned, else " " will be returned.
     *
     * @author Tan Kerway
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }
    /**
     * Returns whether the task has been done or not
     *
     * @author Tan Kerway
     * @return true if the task is done, false otherwise
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns the description of the task instance
     *
     * @author Tan Kerway
     * @return the description of the task instance
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the isDone task flag to true.
     *
     * @author Tan Kerway
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Sets the isDone task flag to false.
     *
     * @author Tan Kerway
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Sets the isValid flag to false.
     *
     * @author Tan Kerway
     * @return true if valid, false otherwise
     */
    public boolean isValid() {
        return this.isValid;
    }

    /**
     * Sets the isValid flag to false.
     *
     * @author Tan Kerway
     */
    public void setInvalid() {
        this.isValid = false;
    }

    /**
     * Overrides the toString method.
     *
     * @author Tan Kerway
     * @return the string representation of a task
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
