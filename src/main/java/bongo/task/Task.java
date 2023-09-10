package bongo.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import bongo.helper.BongoException;
/**
 * A class for a Task.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * A constructor for a Task.
     *
     * @param description Task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns status icon of task.
     *
     * @return Status icon of task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Checks if task is scheduled on a particular date.
     *
     * @param date The scheduled date.
     * @return Whether task is scheduled for the date.
     */
    public boolean isTaskScheduledForDate(String date) {
        return false;
    }

    /**
     * Converts datetime into formatted string.
     *
     * @param datetime Datetime of Task.
     * @return Returns formatted datetime string.
     */
    public String generateDateTimeString(LocalDateTime datetime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMM d, yyyy hh:mm a");
        return formatter.format(datetime);
    }

    /**
     * Returns string to save in text file.
     *
     * @return String to save in text file.
     */
    public abstract String generateStringForTextFile() throws BongoException;

    /**
     * Returns string representation of task.
     *
     * @return String representation of task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
