package duke.tasks;

import java.time.LocalDate;

import duke.DatesAndTimesFormatter;

/**
 * Represents an Event object that is a Task.
 */
public class Event extends Task {
    protected String from;
    protected String to;
    protected LocalDate startDate;
    protected LocalDate endDate;

    /**
     * Creates an Event object.
     *
     * @param description The description of the Event.
     * @param from        The start date of the Event.
     * @param to          The end date of the Event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        this.startDate = Task.parseDate(from);
        this.endDate = Task.parseDate(to);
        if (startDate != null) {
            this.from = startDate.format(DatesAndTimesFormatter.OUTPUT_FORMAT.formatter);
        }
        if (endDate != null) {
            this.to = endDate.format(DatesAndTimesFormatter.OUTPUT_FORMAT.formatter);
        }
    }

    @Override
    public String toString() {
        return "|E| " + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
