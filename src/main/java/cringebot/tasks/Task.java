package cringebot.tasks;

import java.io.Serializable;

/**
 * Super class for tasks.
 */
public class Task implements Serializable {
    private String name;
    private boolean isMarked;

    /**
     * Returns the name of the task.
     *
     * @param name name of the task.
     */
    public Task(String name) {
        this.name = name;
        this.isMarked = false;
    }

    /**
     * Indicates the task as marked.
     */
    public void markTask() {
        this.isMarked = true;
    }

    /**
     * Indicates the task as un-marked.
     */
    public void unMarkTask() {
        this.isMarked = false;
    }

    /**
     * Returns the boolean result of whether the task is marked.
     *
     * @return whether the task is marked or not.
     */
    public boolean isMarked() {
        return this.isMarked;
    }

    /**
     * Gets the content of the task.
     *
     * @return content of the task.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Adds a string of text to the end of the current task content.
     *
     * @param addition String to be added to the current task content.
     */
    public void editName(String addition) {
        this.name += addition;
    }
}