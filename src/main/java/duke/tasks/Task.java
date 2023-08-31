package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a single task
 */
public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the status icon
     * @return returns a string representation of the status icon
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    public String toFile() {
        return " | " + (this.isDone ? 1 : 0) + " | " + this.description + " | ";
    }

    public void setAsDone() {
        this.isDone = true;
    }

    public void setAsUndone() {
        this.isDone = false;
    }

    public  String localDateTimeToString(LocalDateTime dateTime) {
        String dayOfMonth = dateTime.getDayOfMonth() + getDayOfMonthSuffix(dateTime.getDayOfMonth());
        String month = dateTime.getMonth().toString();
        month = month.substring(0,1).toUpperCase() + month.substring(1).toLowerCase();// Capitalize the month name
        String year = String.valueOf(dateTime.getYear());
        String hour = dateTime.format(DateTimeFormatter.ofPattern("h"));
        String minute = dateTime.format(DateTimeFormatter.ofPattern("mm"));
        String amPm = dateTime.format(DateTimeFormatter.ofPattern("a"));

        return String.format("%s of %s %s, %s:%s%s", dayOfMonth, month, year, hour, minute, amPm);
    }

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

    public boolean isDone() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }
}
