package moss;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a specific start time and end time.
 */
public class Event extends Task {

    /**
     * The start date of the event.
     */
    private LocalDate fromDate;

    /**
     * The end date of the event.
     */
    private LocalDate toDate;

    /**
     * Constructs a new Event object with the given description, start date, and end date.
     *
     * @param description The description of the event.
     * @param fromDate The start date of the event.
     * @param toDate The end date of the event.
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

    /**
     * Returns a string representation of the Event object.
     *
     * @param x A placeholder parameter to differentiate this method signature.
     * @return A formatted string containing event type, description, start date, and end date.
     */
    @Override
    public String toString(String x) {
        return "E | " + super.toString() + " | " +
                fromDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " | " +
                toDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Returns a string representation of the Event object.
     *
     * @return A string containing event type, description, unformatted start date, and unformatted end date.
     */
    @Override
    public String toString() {
        return "E | " + super.toString() + " | " + fromDate + " | " + toDate;
    }
}

