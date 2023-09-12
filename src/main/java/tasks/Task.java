package tasks;

import java.time.format.DateTimeFormatter;

import exceptions.DukeException;

/**
 * Represents a task.
 */
public abstract class Task {
    /** Datetime format for storage and input */
    protected static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    /** Datetime format for output */
    protected static final DateTimeFormatter DATE_FORMAT_OUTPUT = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
    /** Description of the task */
    private String description;

    /** Indicator if task is done */
    private boolean isDone;

    /**
     * Constructor for Task.
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor for Task.
     * @return String if task is done
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Sets the status of the task.
     * @param statusIcon "X" if task is done, " " if task is not done
     */
    public void setStatusIcon(String statusIcon) {
        this.isDone = statusIcon.equals("X");
    }
    /**
     * Returns the description of the task.
     * @return String description
     */
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public static DateTimeFormatter getDateFormat() {
        return DATE_FORMAT;
    }

    public static DateTimeFormatter getDateFormatOutput() {
        return DATE_FORMAT_OUTPUT;
    }

    /**
     * Marks task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks task as undone.
     */
    public void markAsUndone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns the string representation of the task to be saved in a file.
     * @return String representation of the task
     */
    public abstract String toFileString();

    /**
     * Convert from a string to a task.
     * @param fileString Stored list in String format within the file
     * @throws DukeException
     */
    public abstract void fromFileString(String fileString) throws DukeException;
}
