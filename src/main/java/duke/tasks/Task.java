package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a single task with a description and completion status.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructs a Task instance with the given description and sets completion status to false.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a Task instance with the given description and completion status.
     *
     * @param description The description of the task.
     * @param isDone The completion status of the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return The status icon ("X" if done, " " if not done).
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Converts the task to its string representation.
     *
     * @return The string representation of the task.
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    /**
     * Converts the task to its file format representation.
     *
     * @return The file format representation of the task.
     */
    public String toFile() {
        return " | " + (this.isDone ? 1 : 0) + " | " + this.description + " | ";
    }

    /**
     * Marks the task as done.
     */
    public void setAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void setAsUndone() {
        this.isDone = false;
    }

    /**
     * Converts a LocalDateTime object to a formatted string representation.
     *
     * @param dateTime The LocalDateTime object to be converted.
     * @return The formatted string representation of the date and time.
     */
    public String localDateTimeToString(LocalDateTime dateTime) {
        String dayOfMonth = dateTime.getDayOfMonth() + getDayOfMonthSuffix(dateTime.getDayOfMonth());
        String month = dateTime.getMonth().toString();
        month = month.substring(0, 1).toUpperCase() + month.substring(1).toLowerCase();
        String year = String.valueOf(dateTime.getYear());
        String hour = dateTime.format(DateTimeFormatter.ofPattern("h"));
        String minute = dateTime.format(DateTimeFormatter.ofPattern("mm"));
        String amPm = dateTime.format(DateTimeFormatter.ofPattern("a"));

        return String.format("%s of %s %s, %s:%s%s", dayOfMonth, month, year, hour, minute, amPm);
    }

    /**
     * Returns the suffix for the day of the month.
     *
     * @param n The day of the month.
     * @return The suffix ("st", "nd", "rd", or "th").
     */
    public String getDayOfMonthSuffix(final int n) {
        if (n >= 11 && n <= 13) {
            return "th";
        }
        switch (n % 10) {
            case 1:
                return "st";
            case 2:
                return "nd";
            case 3:
                return "rd";
            default:
                return "th";
        }
    }

    /**
     * Checks if the task is marked as done.
     *
     * @return True if the task is done, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }
}
