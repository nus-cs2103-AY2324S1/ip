package alyssa;

/**
 * An event is a task with a start date/time and
 * an end date/time.
 */
public class Event extends Task {
    /** The date/time this event starts from. */
    private String from;
    /** The date/time this event ends. */
    private String to;

    /**
     * Constructor method for alyssa.Event.
     * @param description A description for the event.
     * @param from A string representing when the event starts.
     * @param to A string representing when the event ends.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Returns from of this event.
     */
    public String getFrom() {
        return this.from;
    }
    /**
     * Returns to of this event.
     */
    public String getTo() {
        return this.to;
    }
}
