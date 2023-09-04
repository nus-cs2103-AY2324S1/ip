package duke.task;

import java.time.format.DateTimeFormatter;

/**
 * Represents a task to be used by the Duke chat-bot.
 */
public abstract class Task {
    protected static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("d MMM yyyy, HH:mm");
    protected String description;
    protected boolean isDone;

    /**
     * Creates a Task object.
     *
     * @param description The description of the task.
     * @param isDone      Whether the task is done.
     */
    protected Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Checks if the task description matches the specified keyword.
     *
     * @param keyword The keyword to search for.
     * @return True if the task description matches the specified keyword, false otherwise.
     */
    public boolean hasMatchingDescription(String keyword) {
        return this.description.contains(keyword);
    }

    /**
     * Marks the task as done.
     *
     * @return The task.
     */

    public Task markAsDone() {
        this.isDone = true;
        return this;
    }

    /**
     * Marks the task as undone.
     *
     * @return The task.
     */
    public Task markAsUndone() {
        this.isDone = false;
        return this;
    }

    /**
     * Returns the task encoded for storage for the Duke chat-bot.
     *
     * @return The task encoded for storage for the Duke chat-bot.
     */
    public abstract String encodeTask();

    /**
     * Returns the string representation of the task.
     *
     * @return The string representation of the task.
     */
    public String toString() {
        return String.format("[%s] %s", this.isDone ? "X" : " ", this.description);
    }
}
