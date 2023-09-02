package duke.tasks;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Adapted from Partial Solution given in Level-3 of
 * https://nus-cs2103-ay2324s1.github.io/website/schedule/week2/project.html
 * 
 * Parent tasks class to create tasks objects
 * Tasks have a desciption of what the task is and 
 * a boolean to indicate if it is done
 */
public abstract class Task {

    /**
     * The description of the task.
     */
    protected String description;

    /**
     * Whether the task is done.
     */
    protected boolean isDone;

    /**
     * The date/time format of the reading the task input from the user and file.
     */
    protected DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * The date/time format of the output of the task to the user.
     */
    protected DateTimeFormatter DATE_TIME_OUTPUT = DateTimeFormatter.ofPattern("MMM dd yyyy");
    /**
     * Creates a Task object.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Creates a Task object.
     *
     * @param description The description of the task.
     * @param isDone Whether the task is done.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns whether the task is done.
     * 
     * @return Whether the task is done.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Returns the string representation of the task to be written to the file.
     * 
     * @return The string representation of the task to be written to the file.
     */
    public abstract String toFileString();
}