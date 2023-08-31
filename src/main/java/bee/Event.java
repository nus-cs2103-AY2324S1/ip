package bee;

/**
 * Represents an event task with start and end dates.
 * Extends the base class Task and provides functionality for handling events.
 */
public class Event extends Task{
    String startDate;
    String endDate;

    /**
     * Constructs a new Event task with start and end dates.
     *
     * @param description Description of the event task.
     * @param startDate   Start date of the event.
     * @param endDate     End date of the event.
     */
    public Event(String description, String startDate, String endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Constructs a new Event task with start and end dates and an additional completion status.
     *
     * @param description Description of the event task.
     * @param startDate   Start date of the event.
     * @param endDate     End date of the event.
     * @param isDone      Completion status of the event task.
     */
    public Event(String description, String startDate, String endDate, Boolean isDone) {
        super(description, isDone);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Converts the Event task to a formatted string representation.
     *
     * @return Formatted string representation of the Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startDate + " to: " + endDate + ")";
    }
}
