/**
 * Represents an Event, a task that start at a specific date/time and ends at a specific date/time.
 */
public class Event extends Task {
    protected String start;
    protected String end;

    /**
     * Creates an event object.
     * @param description Description of event.
     * @param start Start of event.
     * @param end End of event.
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the string representation of the event.
     * @return string representation of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }
}
