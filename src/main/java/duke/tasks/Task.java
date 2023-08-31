package duke.tasks;

import java.time.LocalDateTime;

/**
 * Represents a task in the chat bot.
 */
public class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Constructs a task with the given description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    /**
     * Constructs a task with the given description and done status.
     *
     * @param description The description of the task.
     * @param isDone      The done status of the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markUndone() {
        isDone = false;
    }

    /**
     * Gets the status icon of the task.
     *
     * @return The string representation of the status icon.
     */
    public String getStatus() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Returns a string representation of the task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("%s %s", getStatus(), description);
    }

    /**
     * Formats the task for storage in a data file.
     *
     * @return The formatted string for storage.
     */
    public String formatForStorage() {
        return String.format("%s | %s", isDone ? "1" : "0", description);
    }

    /**
     * Checks if the task is within the given date range.
     *
     * @param date The date to check against.
     * @return True if the task is within the date range, false otherwise.
     */
    public boolean isWithinDateRange(LocalDateTime date) {
        return false;
    }

    /**
     * Checks if the task's description contains the given keyword.
     *
     * @param keyword The keyword to search for.
     * @return True if the description contains the keyword, false otherwise.
     */
    public boolean isRelatedContent(String keyword) {
        return description.contains(keyword);
    }

}
