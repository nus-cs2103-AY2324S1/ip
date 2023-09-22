package chatbuddy.task;

/**
 * Task represents a Task object in ChatBuddy.
 * A task has a description and a boolean representing whether the task is done.
 */
public class Task {
    /** The description of the task. */
    protected String description;

    /** The boolean representing whether the task is done. */
    protected boolean isDone;

    /** The tag associated with the task. */
    protected String tag;

    /**
     * Creates a Task object with a given description.
     * The task object is not done by default.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
        tag = "";
    }

    /** Marks the task as done. */
    public void markAsDone() {
        isDone = true;
    }

    /** Marks the task as not done. */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Returns the status icon of the task based on whether the task is done.
     *
     * @return The status icon of the task. Returns X if task is done, else returns an empty space.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the tag of the task with " #" appended in front of the tag.
     *
     * @return The tag of the task with " #" appended in front. Returns an empty string if the tag is an empty string.
     */
    public String getTag() {
        return tag.equals("") ? "" : " #" + tag;
    }

    @Override
    public String toString() {
        return String.format("[%1s] %2s", getStatusIcon(), description) + getTag();
    }

    /**
     * Returns task information in format for saving.
     * The format is [1 if completed, 0 if not completed] | [task description].
     *
     * @return The task information in format for saving.
     */
    public String getInformationForSaving() {
        return String.format("%1s | %2s | ", isDone ? "1" : "0", description) + tag;
    }

    /**
     * Checks if the description of the task contains the given keyword.
     *
     * @param keyword The keyword to search for.
     * @return True if the description contains the keyword, false otherwise.
     */
    public boolean containKeyword(String keyword) {
        return description.toLowerCase().contains(keyword.toLowerCase());
    }

    /**
     * Changes the tag of the task to the given tag.
     *
     * @param newTag The tag to change to.
     */
    public void updateTag(String newTag) {
        tag = newTag;
    }
}
