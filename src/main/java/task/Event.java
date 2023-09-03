package task;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Event is a Task that has a start date/time and an end date/time.
 */
public class Event extends Task {
    protected LocalDate startDate;
    protected LocalTime startTime;
    protected String start;
    protected LocalDate endDate;
    protected LocalTime endTime;
    protected String end;

    /**
     * The constructor of Event.
     *
     * @param description the event description.
     * @param startDate the start date of the event.
     * @param startTime the start time of the event.
     * @param endDate the end date of the event.
     * @param endTime the end time of the event.
     */
    public Event(String description, LocalDate startDate, LocalTime startTime,
                 LocalDate endDate, LocalTime endTime) {
        super(description);
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
        this.start = startDate.toString() + " " + startTime.toString();
        this.end = endDate.toString() + " " + endTime.toString();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }
}
