package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class encapsulates the description of the task and the status of the task.
 */
public class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task.
     *
     * @param description the description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the string representation of the status of the task.
     *
     * @return a string consisting of the string representation of the status of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the string representation of the Task.
     *
     * @return a string consisting of the string representation of the Task instance.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the string representation of the line that will be written into a file.
     *
     * @return a string consisting of the string representation that will be written into a file.
     */
    public String convertToSaveFormat() {
        return (isDone ? "X" : " ") + " | " + this.description;
    }

    /**
     * Returns the string representation of the date/time in the format MMM dd yyyy hh:mm.
     * Time is in 12-hours format.
     *
     * @param dateTime the date/time object.
     * @return a string consisting of the date/time in given format.
     */
    public String convertDateToString(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma"));
    }
}
