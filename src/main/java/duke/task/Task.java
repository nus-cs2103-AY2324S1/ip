package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a description and completion status. Tasks can be marked as done or undone.
 */
public abstract class Task {

    /**
     * Description of the task.
     */
    protected String description;

    /**
     * Indicates whether the task is marked as done or not.
     */
    protected boolean isDone;

    /**
     * Constructs a Task object with the given description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status icon representing whether the task is done or not.
     *
     * @return A string representing the status icon ("X" for done, " " for not done).
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done (undoes the markDone operation).
     */
    public void unmarkDone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task, including its completion status.
     *
     * @return A formatted string showing the task's status and description.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

    /**
     * Serializes the task to a string format for saving.
     *
     * @return A serialized string representation of the task.
     */
    public abstract String serialize();

    /**
     * Formats a LocalDateTime object to a string format suitable for serialization.
     *
     * @param date The LocalDateTime object to be formatted.
     * @return A formatted string representing the LocalDateTime.
     */
    public String serializeLocalDateTime(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    /**
     * Formats a LocalDateTime object to a more human-readable string format.
     *
     * @param date The LocalDateTime object to be formatted.
     * @return A formatted string representing the LocalDateTime.
     */
    public String formatLocalDateTime(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
    }

    /**
     * Checks whether the task's description contains the given keyword.
     *
     * @param keyword The keyword to search for in the task's description.
     * @return True if the description contains the keyword, false otherwise.
     */
    public boolean containKeyword(String keyword) {
        return this.description.contains(keyword);
    }
}
