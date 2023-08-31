package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task that can be tracked in
 * the chatbot.
 */
public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Event constructor that takes in String, LocalDateTime, LocalDateTime.
     * @param desc Description of the event.
     * @param from The starting date and time of the event.
     * @param to The ending date and time of the event.
     */
    public Event(String desc, LocalDateTime from, LocalDateTime to) {
        super(desc);
        this.from =  from;
        this.to = to;
    }

    /**
     * Returns the starting date and time of the event.
     * @return Starting date and time of the event.
     */
    public LocalDateTime getFrom() {
        return from;
    }

    /**
     * Returns the ending date and time of the event.
     * @return ending date and time of the event.
     */
    public LocalDateTime getTo() {
        return to;
    }

    /**
     * Returns the string representation of an event.
     * @return The string representation of an event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + from.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + " "
                + "- " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }
}
