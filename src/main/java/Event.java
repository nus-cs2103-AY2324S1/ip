/**
 * Represents an Event object.
 */
public class Event extends Task {
    /**
     * Start time of event.
     */
    protected String from;

    /**
     * End time of event.
     */
    protected String to;

    /**
     * Constructor to create an Event object.
     * @param description Description of event
     * @param from Start time of event
     * @param to End time of event
     */
    public Event(String description, String from, String to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a String representation of an Event.
     * @return String representation of an Event.
     */
    @Override
    public String toString() {
        return "[E][" + this.getStatusIcon() + "] " + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }

    @Override
    public String toSaveFormat() {
        return "E | " + (this.isDone ? "1" : "0") + " | " + super.toString() + " | " + this.from + " | " + this.to;
    }
}