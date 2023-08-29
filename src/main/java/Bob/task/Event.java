package Bob.task;

/**
 * An Event is a task that has a time period at which it occurs or should be completed. Hence,
 * it contains a start and end date to represent the time period.
 */
public class Event extends Task {
    protected String startDateTime;
    protected String endDateTime;

    /**
     * Constructor of the Event Class.
     *
     * @param description
     * @param startDateTime
     * @param endDateTime
     */
    public Event(String description, String startDateTime, String endDateTime) {
        super(description);
        this.startDateTime = DateFormatter.format(startDateTime, "MMM d yyyy");
        this.endDateTime = DateFormatter.format(endDateTime, "MMM d yyyy");
    }

    /**
     * Constructor of the Event Class.
     * Instantiates the event and marks its completion status based on boolean provided.
     *
     * @param description
     * @param startDateTime
     * @param endDateTime
     * @param isDone
     */
    public Event(String description, String startDateTime, String endDateTime, boolean isDone) {
        super(description, isDone);
        this.startDateTime = DateFormatter.format(startDateTime, "MMM d yyyy");
        this.endDateTime = DateFormatter.format(endDateTime, "MMM d yyyy");
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s, to: %s)", super.toString(), this.startDateTime, this.endDateTime);
    }

    @Override
    public String convertToFileFormat() {
        return String.format("E|%s|%s|%s", super.convertToFileFormat(), this.startDateTime, this.endDateTime);
    }
}
