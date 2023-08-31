package taskmaster.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    /**
     * Starting time of event in string type.
     */
    String startTime;
    /**
     * End time of event in string type.
     */
    String endTime;
    /**
     * Start date of event.
     */
    LocalDate startDate;
    /**
     * End date of event.
     */
    LocalDate endDate;

    /**
     * Constructor for the Event class.
     *
     * @param description Description of the Deadline.
     * @param start Time that the event starts.
     * @param marked Boolean whether the event is marked.
     */
    public Event(String description, String start, String end, String marked) {
        super(description, marked);
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate parsedStartDate = LocalDate.parse(start, formatter);
            LocalDate parsedEndDate = LocalDate.parse(end, formatter);
            this.startDate = parsedStartDate;
            this.endDate = parsedEndDate;
        } catch (java.time.format.DateTimeParseException e) {
            this.startTime = start;
            this.endTime = end;
        }
    }

    /**
     * Returns a string representation of the start time.
     *
     * @return A string representing the time of the event.
     */
    public String getStartString() {
        return this.startTime;
    }

    /**
     * Returns a LocalDate representation of the start date.
     *
     * @return A LocalDate representing the starting date of the event.
     */
    public LocalDate getStartDate() {
        return this.startDate;
    }

    /**
     * Returns a string representation of the event.
     *
     * @return A string representing the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + (startTime == null ? this.startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) : startTime)
                + " to "
                + (endTime == null ? this.endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) : endTime)
                + ")";
    }
}
