package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


 /** Represents a generic task.
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
     * Returns the icon representing the completion status of the task.
     *
     * @return An "X" if the task is done, or a space if the task is not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Retrieves the completion status of the task.
     *
     * @return True if the task is done, false otherwise.
     */
    public boolean getStatus() {
        return this.isDone;
    }

    /**
     * Returns a string representation of the Task, including its completion status and description.
     *
     * @return A string representation of the Task.
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Converts a LocalDateTime object to a formatted string.
     * @param data The LocalDateTime object to be converted.
     * @return A formatted string representing the date and time.
     */
    public String localDateTimeToString(LocalDateTime data) {
        String day = String.valueOf(data.getDayOfMonth());
        String month = data.getMonth().toString();
        month = month.substring(0, 1).toUpperCase() + month.substring(1).toLowerCase();
        String year = String.valueOf(data.getYear());
        String hour = data.format(DateTimeFormatter.ofPattern("h"));
        String minute = data.format(DateTimeFormatter.ofPattern("mm"));
        String amPm = data.format(DateTimeFormatter.ofPattern("a"));

        return String.format("%s of %s %s, %s:%s%s", day, month, year, hour, minute, amPm);
    }
}
