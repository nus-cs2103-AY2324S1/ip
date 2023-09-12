package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a generic task.
 * A task has a description and can be marked as done or undone.
 * This class serves as the base class for different types of tasks (e.g., Todo, Deadline, Event).
 */
public class Task {

    /** The description of the task. */
    public String description;

    /** A flag indicating whether the task is marked as done (true) or undone (false). */
    protected boolean isDone;

    /**
     * Creates a new Task with the given description.
     *
     * @param description A description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns an icon representing the status of the task.
     *
     * @return "X" if the task is done, or a space if the task is undone.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Retrieves the status of the task (done or undone).
     *
     * @return true if the task is done, false if it is undone.
     */
    public boolean getStatus() {
        return this.isDone;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string in the format: "[Status Icon] Task Description".
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " +  this.description;
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone (not done yet).
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Converts a LocalDateTime object to a formatted string.
     *
     * @param data The LocalDateTime object to convert.
     * @return A formatted string representation of the LocalDateTime object.
     */
    public String localDateTimeToString(LocalDateTime data) {
        String day = String.valueOf(data.getDayOfMonth());
        String month = data.getMonth().toString();
        month = month.substring(0,1).toUpperCase() + month.substring(1).toLowerCase();
        String year = String.valueOf(data.getYear());
        String hour = data.format(DateTimeFormatter.ofPattern("h"));
        String minute = data.format(DateTimeFormatter.ofPattern("mm"));
        String amPm = data.format(DateTimeFormatter.ofPattern("a"));

        return String.format("%s of %s %s, %s:%s%s", day, month, year, hour, minute, amPm);
    }
}
