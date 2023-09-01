package moss;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDate fromDate;
    private LocalDate toDate;

    /**
     * Constructs an Moss.Event object with the given description, start time, and end time.
     *
     * @param description The description of the event.
     * @param fromDate        The start time of the event.
     * @param toDate          The end time of the event.
     */
    public Event(String description, LocalDate fromDate, LocalDate toDate) {
        super(description);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    /**
     * Returns the start time of the event.
     *
     * @return The start time of the event.
     */
    public LocalDate getFromDate() {
        return fromDate;
    }

    /**
     * Returns the end time of the event.
     *
     * @return The end time of the event.
     */
    public LocalDate getToDate() {
        return toDate;
    }
    @Override
    public String toString(String x) {
        return "E | " + super.toString() + " | " + fromDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " | " + toDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + "";
    }

    @Override
    public String toString() {
        return "E | " + super.toString() + " | " + fromDate + " | " + toDate + "";
    }
}
