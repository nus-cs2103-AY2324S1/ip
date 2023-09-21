package jeo.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task of type event.
 *
 * @author Joseph Oliver Lim
 */
public class Event extends Task {

    protected LocalDate from;
    protected LocalDate to;

    /**
     * Constructs an event with a specified description, start date and end date.
     *
     * @param description A string describing the event name.
     * @param from A string describing the start date.
     * @param to A string describing the end date.
     * @throws DateTimeParseException If the start date or end date is not a valid date.
     */
    public Event(String description, String from, String to) throws DateTimeParseException {
        super(description);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    /**
     * Returns the String representation of the Event.
     *
     * @return String representation of the Event.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    /**
     * Compares the Event Task to another task.
     *
     * @param o the object to be compared.
     * @return The comparison value.
     */
    @Override
    public int compareTo(Task o) {
        if (o instanceof ToDo) {
            return 1;
        }
        if (o instanceof Deadline) {
            return 1;
        }
        if (o instanceof Event) {
            Event event = (Event) o;
            if (this.from.compareTo(event.from) == 0) {
                return this.to.compareTo(event.to);
            }
            return this.from.compareTo(event.from);
        }
        return 0;
    }
}
