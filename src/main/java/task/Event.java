package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a event task with a description, start date/time and end date/time
 */
public class Event extends Task {

    public static final String TASK_TYPE = "E";

    /** Starting date/time of the event */
    protected LocalDateTime from;

    /** Ending date/time of the event */
    protected LocalDateTime to;

    /**
     * Creates an event with the given description, starting date/time and ending date/time of the event
     *
     * @param description description of the task
     * @param from starting date/time of the task
     * @param to ending date/time of the task
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
        this.taskType = TASK_TYPE;
    }

    /**
     * Formats the task into a string to be written to the storage file
     *
     * @return string to be written to the storage file
     */
    public String formatForFileWriting() {
        return this.getDescription() + " | " + this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"))
                + " | " + this.to.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));
    }

    /**
     * Returns a string representation of the task
     *
     * @return string representation of the task
     */
    @Override
    public String toString() {
        return super.toString() + " (from: " + this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"))
                + " to: " + this.to.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")) + ")";
    }
}
