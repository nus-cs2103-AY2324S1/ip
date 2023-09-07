package dukduk;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that is an event with a specific start and end date/time.
 */
public class Event extends Task {

    protected LocalDateTime fromDateTime;
    protected LocalDateTime toDateTime;

    /**
     * Initializes a new Event task with the given description, start date/time, and end date/time.
     *
     * @param description   The description of the event.
     * @param fromDateTime  The start date/time of the event as a LocalDateTime object.
     * @param toDateTime    The end date/time of the event as a LocalDateTime object.
     */
    public Event(String description, LocalDateTime fromDateTime, LocalDateTime toDateTime) {
        super(description);
        this.fromDateTime = fromDateTime;
        this.toDateTime = toDateTime;
    }

    /**
     * Returns a string representation of the Event task, including its description, 
     * start date/time, and end date/time.
     *
     * @return A formatted string representing the Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + 
                fromDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + " to: " + 
                toDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }

    /**
     * Returns a string representation of the Event task in a data-friendly format.
     *
     * @return A formatted string suitable for data storage.
     */
    @Override
    public String toDataString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + 
                fromDateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")) + "|" + 
                toDateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }
}
