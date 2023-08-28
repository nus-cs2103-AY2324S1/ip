/**
 * Represents a task of type Event
 *
 * @author Celestine
 */
public class Event extends Task {

    protected String from;
    protected String to;

    /**
     * A constructor for task of type Event
     *
     * @param description the task details
     * @param from the start date/time for the task
     * @param to the end date/time for the task
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

    public String toFileFormat() {
        return "E | " + this.isDone + " | " + this.description + " | " + this.from + " | " + this.to;
    }
}
