package duke.tasks;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents an event task that occurs within a specified time range.
 * This class extends the duke.tasks.Task class and adds the event's start and end times.
 */
public class Event extends Task {
    /**
     * The start time of the event.
     */
    protected LocalDate fromDate;
    protected LocalTime fromTime;
    /**
     * The end time of the event.
     */
    protected LocalDate toDate;
    protected LocalTime toTime;
    protected String from;
    protected String to;

    /**
     * Constructs an duke.tasks.Event object with the given description, start date and time, and end date and time.
     *
     * @param description The description of the event.
     * @param fromDate The start date of the event.
     * @param fromTime The start time of the event.
     * @param toDate The end date of the event.
     * @param toTime The end time of the event.
     */
    public Event(String description, LocalDate fromDate, LocalTime fromTime, LocalDate toDate, LocalTime toTime) {
        super(description);
        this.fromDate = fromDate;
        this.fromTime = fromTime;
        this.toDate = toDate;
        this.toTime = toTime;
        this.from = fromDate.toString() + " " + fromTime.toString();
        this.to = toDate.toString() + " " + toTime.toString();
    }

    /**
     * Returns a string representation of the duke.tasks.Event object.
     *
     * @return A formatted string including the task type, description, start time, and end time.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}