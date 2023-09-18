package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.exception.InvalidStartEndDatetimeException;

/**
 * Represents an event.
 *
 * @author Pearlynn
 */

public class Event extends Task {

    /** The start date/time of the event. */
    protected LocalDateTime from;

    /** The end date/time of the event. */
    protected LocalDateTime to;

    /**
     * Constructor for duke.task.Event class.
     *
     * @param description The description of the event.
     * @param from The start date/time of the event.
     * @param to The end date/time of the event.
     */
    public Event(String description, String from, String to) throws InvalidStartEndDatetimeException {
        super(description, from);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.from = LocalDateTime.parse(from, formatter);
        this.to = LocalDateTime.parse(to, formatter);
        if (!(this.from.isBefore(this.to))) {
            throw new InvalidStartEndDatetimeException();
        }
    }

    /**
     * Constructor for duke.task.Event class.
     *
     * @param description The description of the event.
     * @param isDone The status of the event.
     * @param from The start date/time of the event.
     * @param to The end date/time of the event.
     */
    public Event(String description, boolean isDone, String from, String to) throws InvalidStartEndDatetimeException {
        super(description, from, isDone);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.from = LocalDateTime.parse(from, formatter);
        this.to = LocalDateTime.parse(to, formatter);
        if (!(this.from.isBefore(this.to))) {
            throw new InvalidStartEndDatetimeException();
        }
    }

    /**
     * Returns the string representation of the event in the file.
     *
     * @return A string representation of the event in the file.
     */
    @Override
    public String taskStringify() {
        int status = super.isDone ? 1 : 0;
        String fromFormat = this.from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        String toFormat = this.to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        return "E | " + status + " | " + super.description + " | " + fromFormat + " - " + toFormat;
    }

    /**
     * Returns the string representation of the event.
     *
     * @return A string representation of the event.
     */
    @Override
    public String toString() {
        String fromFormat = this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));
        String toFormat = this.to.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));
        return "[E]" + super.toString() + " (from: " + fromFormat + " to: " + toFormat + ")";
    }
}
