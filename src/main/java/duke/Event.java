package duke;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
/**
 * Represents an event task in the Duke application.
 * Each event has a description, start time, and end time.
 */
public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs an Event object.
     *
     * @param description The description of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the event suitable for user display.
     *
     * @return A string representation of the event.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[E]" + super.toString() + " (from: " + from.format(formatter) + " to: " + to.format(formatter) + ")";
    }

    /**
     * Returns a string representation of the event suitable for saving to file.
     *
     * @return A string representation of the event for file storage.
     */
    @Override
    public String toFileString() {
        return "E" + super.toFileString() + " | " + from + " | " + to;
    }
}

