package duke.task;

import java.time.LocalDateTime;

/**
 * The Event class represents a task that occurs during a specified time range.
 * It extends the Task class and includes information about the start and end date and time.
 *
 * @author selwyn
 */
public class Event extends Task {
    /** Start date and time of the event */
    private LocalDateTime startDateTime;

    /** End date and time of the event */
    private LocalDateTime endDateTime;

    /**
     * Constructs an Event object with the specified details, start date and time, and end date and time.
     *
     * @param detail The details of the event.
     * @param startDateTime The start date and time of the event.
     * @param endDateTime The end date and time of the event.
     */
    public Event(String detail, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(detail);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    /**
     * Returns a string representation of the Event object, including its details and time range.
     *
     * @return A string representation of the Event object.
     */
    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + super.getDisplayDateTime(this.startDateTime) + " to: "
                + super.getDisplayDateTime(this.endDateTime) + ")";
    }
}