package duke.task;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents a task.
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructor for Task.
     *
     * @param description description of task.
     */
    public Task(String description) {

        assert !Objects.equals(description, "");

        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns String representing status of task.
     *
     * @return string representing status of task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Changes task to done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Changes task to not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Gets a string representing this event to save to file.
     *
     * @return string representing this event to save to file.
     */
    public String getSaveString() {
        return "";
    }

    /**
     * Returns a description of the event.
     *
     * @return a string representing the description of the event.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the status of the event.
     *
     * @return a boolean representing the status of the event.
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Returns if task is before given date.
     *
     * @param date given date to check against.
     * @return true.
     */
    public boolean isBefore(LocalDate date) {
        return true;
    }

    /**
     * Gets string representation of task.
     *
     * @return string representation of task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
