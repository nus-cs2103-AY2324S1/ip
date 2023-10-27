package yong.tasks;

import java.time.LocalDateTime;

/**
 * Represents an Event task with a description, start time, and end time.
 */
public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructor for the Event class.
     *
     * @param description Description of the task.
     * @param from Start time of the task in the format "yyyy-MM-dd HH:mm".
     * @param to End time of the task in the format "yyyy-MM-dd HH:mm".
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = parseDateTime(from);
        this.to = parseDateTime(to);
    }

    /**
     * String representation of the Event task.
     *
     * @return Information about the event.
     */
    @Override
    public String toString() {
        String ret = "[E] " + super.toString() + " (from: " + printDateTime(this.from) + " to: "
                + printDateTime(this.to) + ")";
        return ret;
    }

    /**
     * Getter method for the start time of the event.
     *
     * @return LocalDateTime object representing the start time.
     */
    public LocalDateTime getCompareDate() {
        return from;
    }
}
