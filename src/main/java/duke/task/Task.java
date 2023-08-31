package duke.task;

import java.time.LocalDate;

import duke.Keyword;
import duke.Storage;

/**
 * Represents a task. A <code>Task</code> object corresponds to a task
 * described by a description and a boolean indicating whether the task
 * is done.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a <code>Task</code> object with the given description.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status of the task, whether it is done or not.
     * <code>"X"</code> represents done, and a <code>" "</code> represents
     * not done.
     *
     * @return Status of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done or not done.
     *
     * @param isDone Status of the task.
     * @return String representation of the task.
     */
    public String mark(boolean isDone) {
        this.isDone = isDone;
        return this.toString();
    }

    /**
     * Returns the file format of the task for
     * it to be stored and read from a file.
     *
     * @return File format of the task.
     */
    public String fileFormat() {
        return String.format("%d%s%s",
                isDone ? 1 : 0, Storage.SEPARATOR, description);
    }

    /**
     * Returns whether the task is on or before the given date.
     *
     * @param key Keyword to check if it is the right type of task.
     * @param date The date to check if the task is on or before.
     * @return Whether the task is on or before the given date.
     */
    public boolean onDate(Keyword key, LocalDate date) {
        return false;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
