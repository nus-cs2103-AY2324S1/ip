package JavAI;
/**
 * Represents an event task that has a description, start time, and end time.
 */
public class Event extends Task {

    /** The end time of the event. */
    protected String to;

    /** The start time of the event. */
    protected String from;

    /**
     * Constructs an event task with a description, start time, and end time.
     *
     * @param description The description of the event task.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.to = to;
        this.from = from;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " ( from: " + from + "to: " + to + ")";
    }
}
