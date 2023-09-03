package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Abstract class that different types of Tasks inherit from.
 */
public abstract class Task {
    /**
     * format of output.
     */
    private static final String DATETIME_OUTPUT_FORMAT = "dd-MM-yyyy HH:mm";

    /**
     * Formatter object to format output.
     */
    public static final DateTimeFormatter DATE_TIME_OUTPUT_FORMATTER =
            DateTimeFormatter.ofPattern(DATETIME_OUTPUT_FORMAT);

    /**
     * Name of task.
     */
    private String taskName;

    /**
     * Boolean to track whether the task has been marked as done.
     */
    private boolean isDone;

    /**
     * Constructor for the task class.
     * Tasks are initialised as not done.
     *
     * @param name Name of the task.
     */
    public Task(String name) {
        this.taskName = name;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string representing the task.
     */
    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + taskName;
        } else {
            return "[ ] " + taskName;
        }
    }

    /**
     * Returns a string representation of the task to be saved.
     *
     * @return A string representing the task to be saved.
     */
    public abstract String toSaveStateString();

    /**
     * Returns the done state of the task.
     *
     * @return A boolean representation of whether the task is done.
     */
    public boolean getDone() {
        return this.isDone;
    }

    /**
     * Returns the name of the task.
     *
     * @return The name of the task.
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Returns whether the task contains a keyword.
     *
     * @return A boolean representation of whether the task contains a keyword.
     */
    public boolean containsKeyword(String keyword) {
        if (this.taskName.contains(keyword)) {
            return true;
        }
        return false;
    }

    /**
     * Returns whether the task is on a date.
     *
     * @return A boolean representation of whether the task is on a given date.
     */
    public abstract boolean isOnDate(LocalDate date);
}
