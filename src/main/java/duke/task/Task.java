package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task.
 *
 * @author Pearlynn
 */

public abstract class Task implements Comparable<Task> {

    /** The description of the task. */
    protected String description;

    /** The status of the task. */
    protected boolean isDone;
    protected LocalDateTime date;

    /**
     * Constructor for duke.task.Task class.
     *
     * @param description The description of the task.
     * @param date The date of the task.
     */
    public Task(String description, String date) {
        this.description = description;
        this.isDone = false;
        if (date == null) {
            this.date = null;
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            this.date = LocalDateTime.parse(date, formatter);
        }
    }

    /**
     * Constructor for duke.task.Task class.
     *
     * @param description The description of the task.
     * @param date The date of the task.
     * @param isDone The status of the task.
     */
    public Task(String description, String date, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        if (date == null) {
            this.date = null;
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            this.date = LocalDateTime.parse(date, formatter);
        }
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the status of the task.
     *
     * @return "X" to mark the task as done, or " " if otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
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
     * Returns the string representation of the task in the file.
     *
     * @return A string representation of the task in the file.
     */
    public abstract String taskStringify();

    /**
     * Compares the tasks based on date.
     *
     * @param t The task to be compared.
     * @return A positive integer, zero, or negative integer if this is greater, equal, or less than t.
     */
    @Override
    public int compareTo(Task t) {
        if (this.date == null) {
            return 1;
        } else if (t.date == null) {
            return -1;
        } else {
            return this.date.compareTo(t.date);
        }
    }

    /**
     * Returns the string representation of the task.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
