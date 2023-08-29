package tasks;

import java.time.format.DateTimeFormatter;

import exceptions.DukeException;

/**
 * Represents a task.
 */
public abstract class Task {
<<<<<<< HEAD
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter DATE_FORMAT_OUTPUT = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
    private String description;
    private boolean isDone;
=======
    /** Description of the task */
    protected String description;

    /** Indicator if task is done */
    protected boolean isDone;

    /** Datetime format for storage and input */
    protected static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    /** Datetime format for output */
    protected static final DateTimeFormatter DATE_FORMAT_OUTPUT = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");

    /**
     * Constructor for Task.
     * @param description
     */
>>>>>>> branch-A-JavaDoc
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
     * @param statusIcon
     */
    public void setStatusIcon(String statusIcon) {
        this.isDone = statusIcon.equals("X");
    }
<<<<<<< HEAD
=======
    
    /**
     * Returns the description of the task.
     * @return String description
     */
>>>>>>> branch-A-JavaDoc
    public String getDescription() {
        return description;
    }

<<<<<<< HEAD
    public void setDescription(String description) {
        this.description = description;
    }
    public static DateTimeFormatter getDateFormat() {
        return DATE_FORMAT;
    }

    public static DateTimeFormatter getDateFormatOutput() {
        return DATE_FORMAT_OUTPUT;
    }

=======
    /**
     * Marks task as done.
     */
>>>>>>> branch-A-JavaDoc
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks task as undone.
     */
    public void markAsUndone() {
        isDone = false;
    }

    /**
     * Returns the string representation of the task for output.
     * @return String
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns the string representation of the task to be saved in a file.
     * @return String
     */
    public abstract String toFileString();

    /**
     * Convert from a string to a task.
     * @param fileString
     * @throws DukeException
     */
    public abstract void fromFileString(String fileString) throws DukeException;
}
