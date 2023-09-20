package bob.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task that contains a description, starting date and ending date.
 */
public class Event extends Task {

    protected LocalDate startDate;
    protected LocalDate endDate;

    /**
     * Constructor for the Event class.
     *
     * @param description the name/description of the event.
     * @param startDate the starting date & time of the event.
     * @param endDate the ending date & time of the event.
     */
    public Event(String description, LocalDate startDate, LocalDate endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String getFrom() {
        return startDate.toString();
    }

    @Override
    public String getTo() {
        return endDate.toString();
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    /**
     * Changes the start and end date of an Event
     *
     * @param newStartDate the new start date of the Event
     * @param newEndDate the new end date of the Event
     */
    public void rescheduleEventDate(LocalDate newStartDate, LocalDate newEndDate) {
        this.startDate = newStartDate;
        this.endDate = newEndDate;
    }

    /**
     * Returns the string representation of this event, including its type of task, completion status,
     * description, and duration (date & time).
     *
     * @return the string representation of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: " + endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
