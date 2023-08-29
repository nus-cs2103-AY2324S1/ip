package grumpygordon.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an grumpygordon.tasks.Event object.
 */
public class Event extends Task {
    /**
     * Start time of event.
     */
    protected LocalDateTime from;

    /**
     * End time of event.
     */
    protected LocalDateTime to;

    /**
     * Constructor to create an grumpygordon.tasks.Event object.
     * @param description Description of event
     * @param from Start time of event
     * @param to End time of event
     */
    public Event(String description, LocalDateTime from, LocalDateTime to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a String representation of an grumpygordon.tasks.Event.
     * @return String representation of an grumpygordon.tasks.Event.
     */
    @Override
    public String toString() {
        return "[E][" + this.getStatusIcon() + "] " + super.toString()
                + " (from: " + this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"))
                + " to: " + this.to.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }

    @Override
    public String toSaveFormat() {
        return "E | " + (this.isDone ? "1" : "0") + " | " + super.toString() + " | " + this.from + " | " + this.to;
    }
}