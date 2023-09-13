package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The object that represents an Event task.
 */
public class Event extends Task {

    private static final String TYPE = "[E]";
    protected LocalDate fromTime;
    protected LocalDate toTime;

    /**
     * Creates an Event object.
     *
     * @param task     Task description.
     * @param fromTime Start time of task.
     * @param toTime   End time of task.
     * @throws DateTimeParseException If any of the time cannot be parsed to LocalDate.
     */
    public Event(String task, String fromTime, String toTime) throws DateTimeParseException {
        super(task);
        assert fromTime != null && toTime != null : "fromTime and toTime input cannot be empty";
        this.fromTime = LocalDate.parse(fromTime);
        this.toTime = LocalDate.parse(toTime);
    }

    /**
     * Checks if the input date falls within the range of the event.
     *
     * @param date The date to check on.
     * @return true if fromtTime <= date <= toTime.
     */
    @Override
    public boolean isOnDate(LocalDate date) {
        boolean isAfterFromTime = date.isAfter(this.fromTime) || date.isEqual(this.fromTime);
        boolean isBeforeToTime = date.isBefore(this.toTime) || date.isEqual(this.toTime);
        return isAfterFromTime && isBeforeToTime;
    }

    @Override
    public String toSaveFormat() {
        return "Event | " + super.toSaveFormat() + " | " + this.fromTime + " | " + this.toTime;
    }

    @Override
    public String toString() {
        return Event.TYPE + super.toString() + " (from: "
                + this.fromTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: " + this.toTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public boolean equals(Object other) {
        if (!super.equals(other)) {
            return false;
        }

        if (!(other instanceof Event)) {
            return false;
        }

        Event o = (Event) other;
        return this.fromTime.equals(o.fromTime) && this.toTime.equals(o.toTime);
    }

}
