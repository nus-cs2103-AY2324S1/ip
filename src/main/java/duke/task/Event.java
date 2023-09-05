package duke.task;

import duke.utility.DateParser;

import java.time.LocalDateTime;

/**
 * Represents an event task with a specified start and end time.
 */
public class Event extends Task {
    private final LocalDateTime to;
    private final LocalDateTime from;

    /**
     * Constructs an Event object.
     *
     * @param name The name or description of the event.
     * @param from The start time of the event.
     * @param to   The end time of the event.
     */
    public Event(String name, String from, String to) {
        super(name);
        this.from = DateParser.convertStringToDateTime(from);
        this.to = DateParser.convertStringToDateTime(to);
    }

    /**
     * Converts the Event object to its string representation.
     *
     * @return The string representation of the Event object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + DateParser.convertDateTimeToString(this.from) + " to: "
                + DateParser.convertDateTimeToString(this.to) + ")";
    }

    /**
     * Converts the Event object to a formatted string for storage.
     *
     * @return The formatted string for storage.
     */
    public String convertTaskToString() {
        return "E | " + (super.isDone() ? "1" : "0") + " | " + super.getName() + " | "
                + DateParser.convertDateTimeToString(this.from) + " | " + DateParser.convertDateTimeToString(this.to);
    }
}
