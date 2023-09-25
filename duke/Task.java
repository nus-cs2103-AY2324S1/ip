package duke;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a general task with a description and optional date and time information.
 * Implements the Serializable interface for storage.
 */
public class Task implements Serializable {
    protected String description;
    protected boolean isDone;
    protected LocalDateTime dateTime;
    protected LocalDate date;

    /**
     * Constructs a Task object with a description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a Task object with a description and a specific date and time.
     *
     * @param description The description of the task.
     * @param dateTime    The LocalDateTime representing the date and time associated with the task.
     */
    public Task(String description, LocalDateTime dateTime) {
        this(description);
        this.dateTime = dateTime;
    }

    /**
     * Returns the status icon for the task.
     * "X" indicates a completed task, and " " indicates an incomplete task.
     *
     * @return The status icon as a string.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done by setting the 'isDone' flag to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone by setting the 'isDone' flag to false.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task, including its status icon and description.
     *
     * @return A formatted string representing the task.
     */
    @Override
    public String toString() {
        String status = "[" + getStatusIcon() + "] ";
        return status + description;
    }
}
