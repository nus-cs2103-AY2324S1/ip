
/**
 * Represents an event task, which is a task that start at a specific date/time and ends at a specific date/time
 */
public class Event extends Task {
    private String from;
    private String to;

    /**
     * Constructs an Event object with the specified description, start time and end time.
     *
     * @param description The description of the event task.
     * @param from        The start time for the event.
     * @param to          The end time for the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}