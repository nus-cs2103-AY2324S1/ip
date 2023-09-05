package chad.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task that the user wants to keep track off.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task instance.
     * @param description the description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status icon representing the task's completion status.
     * @return "X" if the task is done, empty string otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : "");
    }

    /**
     * Gets the completion status of the task as a string.
     * @return "1" if the task is done, "0" otherwise.
     */
    public String isDoneString() {
        return isDone ? "1" : "0";
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Unmarks the task, setting its status to not done.
     */
    public void unMark() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task.
     * @return the string format of the task.
     */
    public abstract String toString();

    /**
     * Returns the task in a format suitable for file storage.
     * @param formatter the DateTimeFormatter to use for date and time representation.
     * @return the string format of the task for file storage.
     */
    public abstract String toFileFormat(DateTimeFormatter formatter);

    // ... rest of the code
}
