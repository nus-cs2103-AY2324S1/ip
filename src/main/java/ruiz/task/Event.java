package ruiz.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task that has a beginning time and an ending time.
 */
public class Event extends Task {
    protected LocalDateTime eventStart;
    protected LocalDateTime eventEnd;
    protected String location;

    /**
     * A constructor the public Event class
     *
     * @param description the description of the event.
     * @param from        the beginning time of the event.
     * @param to          the ending time of the event.
     */
    public Event(String description, String from, String to, String location) throws DateTimeParseException {
        super(description, location);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.eventStart = LocalDateTime.parse(from, formatter);
        this.eventEnd = LocalDateTime.parse(to, formatter);
        this.location = location;
    }

    /**
     * This method converts the value of an Event into a String type.
     *
     * @return the String representation of the event with its type, completion status,
     *     beginning time and ending time.
     */
    @Override
    public String formatSaveTaskString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return "E"
                + super.formatSaveTaskString()
                + " | "
                + eventStart.format(formatter)
                + " | "
                + eventEnd.format(formatter)
                + " | "
                + location;
    }

    /**
     * This method converts the value of an Event into a String type.
     *
     * @return the String representation of the event with its type, completion status,
     *     beginning time and ending time.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        return "[E]"
                + super.toString()
                + " (from: "
                + eventStart.format(formatter)
                + " to: "
                + eventEnd.format(formatter)
                + ")"
                + " at: "
                + location;
    }
}
