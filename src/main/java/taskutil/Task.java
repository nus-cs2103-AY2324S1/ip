package taskutil;

import java.time.format.DateTimeFormatter;

/**
 * Class for Task objects and methods to modify.
 */
public class Task {

    // Format of date to be displayed to user.
    protected static final DateTimeFormatter DISPLAY_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a");

    protected String title;
    protected Boolean isDone;

    /**
     * Constructor for general Task object, called in subclasses.
     *
     * @param title Description of task.
     */
    public Task(String title) {
        this.title = title;
        this.isDone = false;
    }

    /**
     * Gets completed status of task.
     *
     * @return X if task is completed, a blank space otherwise.
     */
    public String getStatus() {
        return (isDone ? "X" : " ");
    }

    /**
     * Modifies status of current task.
     *
     * @param isCompleted New status of task.
     */
    public void setStatus(boolean isCompleted) {
        this.isDone = isCompleted;
    }

    /**
     * Checks if task title contains input query.
     *
     * @return True if task contains query.
     */
    public boolean contains(String query) {
        return this.title.contains(query);
    }

    /**
     * Converts a task to a formatted string.
     *
     * @return Status and title of task in string.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatus(), title);
    }

    /**
     * Returns a base string to be used for storing in data file.
     *
     * @return Formatted string, to be further modified by overriding methods in child classes.
     */
    public String toFileString() {
        return String.format(" | %s | %s", this.getStatus(), title);
    }
}
