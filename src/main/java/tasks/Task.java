package tasks;
import java.time.format.DateTimeFormatter;

import exceptions.DukeException;

/**
 * Represents a task.
 */
public abstract class Task {
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
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor for Task.
     * @return String if task is done
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Sets the status of the task.
     * @param statusIcon
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

    /**
     * Returns the string representation of the task for output.
     * @return String
     */
    @Override
    public String toString(){
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
