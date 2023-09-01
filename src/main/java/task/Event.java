package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a event task with a description, start date/time and end date/time
 */
public class Event extends Task {

    /**
     * Starting date/time of the event
     */
    protected LocalDateTime from;

    /**
     * Ending date/time of the event
     */
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getType() {
        return "E";
    }

    @Override
    public String getDetails() {
        return super.getDetails() + " | " + this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"))
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
