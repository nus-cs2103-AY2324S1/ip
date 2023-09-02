package duke.tasks;

import duke.DatesAndTimesFormatter;

import java.time.LocalDate;

public class Event extends Task {
    protected String from;
    protected String to;
    protected LocalDate startDate;
    protected LocalDate endDate;

    /**
     * Constructor for Event.
     *
     * @param description Description of the event.
     * @param from        Start date of the event.
     * @param to          End date of the event.
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