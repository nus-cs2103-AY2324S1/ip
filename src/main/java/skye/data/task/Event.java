package skye.data.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event that the user wishes to keep track of.
 * An event is a type of task which has a start date and time
 * and an end date and time.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Initialises an event object.
     *
     * @param description A short description of the event
     * @param from Start date and time
     * @param to End date and time
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        setFrom(from);
        setTo(to);
    }

    /**
     * Retrieves the start date and time.
     *
     * @return The start date and time
     */
    public LocalDateTime getFrom() {
        return from;
    }

    /**
     * Sets the start date and time.
     *
     * @param from The start date and time
     */
    public void setFrom(LocalDateTime from) {
        this.from = from;
    }

    /**
     * Retrieves the end date and time.
     *
     * @return The end date and time
     */
    public LocalDateTime getTo() {
        return to;
    }

    /**
     * Sets the end date and time.
     *
     * @param to The end date and time
     */
    public void setTo(LocalDateTime to) {
        this.to = to;
    }

    /**
     * Encodes the event object into a formatted string to be saved into a text file.
     *
     * @return Encoded string of Event object
     */
    @Override
    public String toSaveDataFormat() {
        return String.format("E | %d | %s | %s | %s", isDone() ? 1 : 0, getDescription(),
                getFrom().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")),
                getTo().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
    }

    /**
     * Produces a string representation of an Event object.
     *
     * @return String representation of Event object
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                getFrom().format(DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm")),
                getTo().format(DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm")));
    }

    /**
     * Checks if another event is equivalent to the current event.
     *
     * @param other The other event object we are checking for equivalence
     * @return if the other event object is equivalent to the current object
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof Event) {
            Event otherEvent = (Event) other;
            return (this.description.equals(otherEvent.description))
                    && (this.from.equals(otherEvent.from))
                    && (this.to.equals(otherEvent.to));
        } else {
            return false;
        }
    }
}
