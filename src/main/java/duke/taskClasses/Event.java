package duke.taskClasses;

import duke.exception.InvalidDateTimeException;
import duke.utils.DateTimeUtils;

import java.time.LocalDateTime;

/**
 * Represents an event task.
 * An event task has a description, a start date and time, and an end date and time.
 */
public class Event extends Task {

    /** The start date and time for the event. */
    protected LocalDateTime start;

    /** The end date and time for the event. */
    protected LocalDateTime end;

    /**
     * Constructs a new Event object with a given description, start date, and end date.
     *
     * @param description The description of the event task.
     * @param start The start date and time for the event in string format.
     * @param end The end date and time for the event in string format.
     * @throws InvalidDateTimeException If the provided date strings are not in a valid format.
     */
    public Event(String description, String start, String end) throws InvalidDateTimeException {
        super(description, "E");
        this.start = DateTimeUtils.stringToLocalDateTime(start);
        this.end = DateTimeUtils.stringToLocalDateTime(end);
        this.addedTaskDescription();
    }

    /**
     * Returns a formatted string with details specific to the Event task.
     *
     * @return A string representation of the event details.
     */
    @Override
    public String getDetails() {
        return String.format(" (from: %s to: %s)", DateTimeUtils.localDateTimeToString(this.start), DateTimeUtils.localDateTimeToString(this.end));
    }

    /**
     * Returns a formatted string suitable for database storage.
     *
     * @return A string representation of the event task formatted for database storage.
     */
    public String getDBString() {
        return String.format("%s | %s | %s | %s | %s",
                "E",
                this.isDone() ? "1" : "0",
                this.description,
                DateTimeUtils.localDateTimeToStringForDb(this.start),
                DateTimeUtils.localDateTimeToStringForDb(this.end));
    }
}
