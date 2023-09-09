package dre.task;

import java.time.LocalDate;

/**
 * Represents a generic task.
 */
public class Task {
    private String description;
    private boolean done;

    /**
     * Creates a default generic task.
     */
    public Task() {
        description = "default";
        done = false;
    }

    /**
     * Creates a new generic task.
     *
     * @param newTask The description of the task.
     */
    public Task(String newTask){
        description = newTask;
        done = false;
    }

    /**
     * Checks if the task is done.
     *
     * @return true if the task is done, false otherwise.
     */
    public boolean isDone() {
        return done;
    }

    /**
     * Marks the task as done, no matter the current status of task.
     */
    public void done() {
        done = true;
    }

    /**
     * Unmarks the task as not done, no matter the current status of task.
     */
    public void undo() {
        done = false;
    }

    /**
     * For display and saving purposes, shows whether the task is done.
     *
     * @return The status icon.
     */
    public String getStatusIcon() {
        return (done ? "X" : " ");
    }

    /**
     * Provides a formatted string for saving this task to a file.
     *
     * @return A formatted string suitable for file storage.
     */
    public String fileSaveFormat() {
        return getStatusIcon() + "|" + description;
    }

    /**
     * Converts the task to a string format for display.
     *
     * @return A formatted string representing this task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}