package taskutil;

import java.time.format.DateTimeFormatter;

public class Task {

    // Format of date to be displayed to user.
    protected static final DateTimeFormatter displayFormat = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a");

    protected String title;
    protected Boolean isDone;

    public Task(String title) {
        this.title = title;
        this.isDone = false;
    }

    /**
     * Get completed status of task.
     * @return X if task is completed, a blank space otherwise.
     */
    public String getStatus() {
        return (isDone ? "X" : " ");
    }

    /**
     * Modify status of current task.
     * @param isCompleted New status of task.
     */
    public void changeStatus(boolean isCompleted) {
        this.isDone = isCompleted;
    }

    /**
     * Converts a task to a formatted string.
     * @return Status and title of task in string.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatus(), title);
    }

    /**
     * Returns a base string to be used for storing in data file.
     * @return Formatted string, to be further modified by overriding methods in child classes.
     */
    public String toFileString() {
        return String.format(" | %s | %s", this.getStatus(), title);
    }
}
