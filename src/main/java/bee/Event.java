package bee;

/**
 * Represents an event task with start and end dates.
 * Extends the base class Task and provides functionality for handling events.
 */
public class Event extends Task {
    private String startDate;
    private String endDate;

    /**
     * Constructs a new Event task with start and end dates.
     *
     * @param description Description of the event task.
     * @param startDate   Start date of the event.
     * @param endDate     End date of the event.
     */
    public Event(String description, String startDate, String endDate) {
        super(description);
        assert description != null : "Description cannot be null."; // Assumption: Description should not be null
        assert startDate != null : "StartDate cannot be null."; // Assumption: StartDate should not be null
        assert endDate != null : "EndDate cannot be null."; // Assumption: EndDate should not be null
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
        assert description != null : "Description cannot be null."; // Assumption: Description should not be null
        assert startDate != null : "StartDate cannot be null."; // Assumption: StartDate should not be null
        assert endDate != null : "EndDate cannot be null."; // Assumption: EndDate should not be null
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
