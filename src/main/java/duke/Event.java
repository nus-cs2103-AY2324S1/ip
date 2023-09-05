package duke;

import java.util.*;
import java.time.*;
import java.time.format.*;

/**
 * Represents a task with a specific event duration.
 */
public class Event extends Task {
    protected LocalDate by;
    protected LocalDate from;

    /**
     * Constructs an Event object with the specified description, start date, and end date.
     *
     * @param description The description of the event.
     * @param from        The start date of the event.
     * @param by          The end date of the event.
     */
    public Event(String description, LocalDate from, LocalDate by) {
        super(description);
        this.by = by;
        this.from = from;
    }

    /**
     * Gets the end date of the event.
     *
     * @return The end date.
     */
    public LocalDate getBy() {
        return this.by;
    }

    /**
     * Gets the start date of the event.
     *
     * @return The start date.
     */
    public LocalDate getFrom() {
        return this.from;
    }

    /**
     * Changes the format of a LocalDate to "dd LLLL yyyy".
     *
     * @param date The LocalDate to be formatted.
     * @return The formatted date as a string.
     */
    public String changeFormat(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        String dateFormatted = date.format(formatter);
        return date.format(formatter);
    }

    /**
     * Returns a string representation of the event task.
     *
     * @return A string representation including task type, description, start date, and end date.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + changeFormat(from) + " to: " + changeFormat(by) + ")";
    }
}
