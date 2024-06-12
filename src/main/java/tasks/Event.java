package tasks;

import helpers.DateTimeDetection;

import java.time.LocalDateTime;

/**
 * This class encapsulates an Event child class
 * that contains a description, a start date/time and an end date/time.
 */
public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;

    /**
     * Constructs an Event object.
     *
     * @param description
     * @param start start date/time of the Event.
     * @param end end date/time of the Event.
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = getDateTime(start);
        this.end = getDateTime(end);
    }

    /**
     * Constructs an Event object that specifies whether it has been completed.
     *
     * @param description
     * @param isDone
     * @param start start date/time of the Event.
     * @param end end date/time of the Event.
     */
    public Event(String description, boolean isDone, String start, String end) {
        super(description, isDone);
        this.start = getDateTime(start);
        this.end = getDateTime(end);
    }

    /**
     * Sets the String input of the end date/time to a LocalDateTime
     *
     * @param input In the form of yyyy-MM-dd HH:mm. Either Date or Time may be omitted but not both.
     * @return The corresponding LocalDateTime object.
     */
    public LocalDateTime getDateTime(String input) {
        String[] parts = input.split(" ", 2);
        return DateTimeDetection.detectDateTime(parts[1]);
    }

    public LocalDateTime getStart() {
        return this.start;
    }

    public LocalDateTime getEnd() {
        return this.end;
    }

    @Override
    public String toText() {
        return "E " + this.getDoneStatus() + " " + this.description + " /from "
                + this.start.toString().replace("T", " ")
                + " /to " + this.end.toString().replace("T", " ");
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " ("
                + DateTimeDetection.formatDateTime(this.start) + " - "
                + DateTimeDetection.formatDateTime(this.end) + ")";
    }
}
