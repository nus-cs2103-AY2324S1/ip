package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

import duke.Keyword;
import duke.Storage;
import duke.Time;

/**
 * Represents an event task with a start and end date/time. A <code>Event</code> object
 * corresponds to a task represented by a description, a start date/time and an end date/time.
 */
public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs a <code>Event</code> object with the given description,
     * start date/time and end date/time.
     *
     * @param description Description of the event.
     * @param from Start date/time of the event.
     * @param to End date/time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the file format of the event task.
     *
     * @return File format of the event task.
     */
    @Override
    public String fileFormat() {
        return String.format("E%s%s%s%s%s%s",
                Storage.SEPARATOR, super.fileFormat(),
                Storage.SEPARATOR, this.from.format(Time.DATE_TIME_FORMATTER),
                Storage.SEPARATOR, this.to.format(Time.DATE_TIME_FORMATTER));
    }

    /**
     * Checks if the event task is on the given date. Returns true
     * if the given date is between the start and end date of the event task (inclusive).
     * Otherwise, returns false.
     *
     * @param key Keyword to check if it is the right type of task.
     * @param date The given date to check if the event task is on.
     * @return Whether the event task is on the given date.
     */
    @Override
    public boolean onDate(Keyword key, LocalDate date) {
        LocalDate from = this.from.toLocalDate();
        LocalDate to = this.to.toLocalDate();
        return key.equals(Keyword.EVENT)
                && (from.isBefore(date) || from.equals(date))
                && (to.isAfter(date) || to.equals(date));
    }

    /**
     * Returns the string representation of the event task.
     *
     * @return String representation of the event task.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                this.from.format(Time.DATE_TIME_DISPLAY_FORMATTER),
                this.to.format(Time.DATE_TIME_DISPLAY_FORMATTER));
    }
}
