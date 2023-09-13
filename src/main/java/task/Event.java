package task;

import java.time.LocalDateTime;

import utils.DateTimeFormat;

/**
 * Event is a Task that has a start date/time and an end date/time.
 */
public class Event extends Task {
    protected String fromString;
    protected String toString;

    /**
     * The constructor of Event.
     *
     * @param description The event description.
     * @param fromString The string representation of the from datetime.
     * @param toString The string representation of the to datetime.
     */
    public Event(String description, String fromString, String toString) {
        super(description);
        this.fromString = fromString;
        this.toString = toString;
    }

    /**
     * The constructor of Event with specified status.
     *
     * @param description The event description.
     * @param fromString The string representation of the from datetime.
     * @param toString The string representation of the to datetime.
     * @param isDone The status of the event.
     */
    public Event(String description, String fromString, String toString, boolean isDone) {
        super(description, isDone);
        this.fromString = fromString;
        this.toString = toString;
    }

    /**
     * To get the start date and time of the Event.
     *
     * @return The LocalDateTime object of the start date time of the Event.
     */
    public LocalDateTime getStartDateTime() {
        return DateTimeFormat.toLocalDateTime(this.fromString);
    }

    /**
     * To get the end date and time of the Event.
     *
     * @return The LocalDateTime object of the end date time of the Event.
     */
    public LocalDateTime getEndDateTime() {
        return DateTimeFormat.toLocalDateTime(this.toString);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + fromString + " to: " + toString + ")";
    }
}

