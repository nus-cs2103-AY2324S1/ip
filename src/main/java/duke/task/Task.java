package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

/**
 * Abstract class to represent a task.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /** Returns a string representation of the task to be stored in the data file. */
    abstract public String getDataString();

    /*
     * Constructor for Task.
     * 
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    protected String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    protected String getDescription() {
        return this.description;
    }

    protected String formatDateTime(TemporalAccessor temporalAccessor) {
        if (temporalAccessor instanceof LocalDateTime) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLL yyyy, KK:mma");
            return formatter.format(temporalAccessor);
        }

        if (temporalAccessor instanceof LocalDate) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLL yyyy");
            return formatter.format(temporalAccessor);
        }

        return "";
    }

    /**
     * Marks the task as done.
     * 
     * @return The task itself.
     */
    public Task markAsDone() {
        this.isDone = true;
        return this;
    }

    /**
     * Marks the task as not done.
     * 
     * @return The task itself.
     */
    public Task markAsUndone() {
        this.isDone = false;
        return this;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.getDescription());
    }
}