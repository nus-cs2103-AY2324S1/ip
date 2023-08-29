package chatbot.alain;

/**
 * Represents a task that is an event occurring within a specific time range.
 */
public class Events extends Task {

    /**
     * The starting time of the event.
     */
    protected String from;

    /**
     * The ending time of the event.
     */
    protected String to;

    /**
     * Constructs an Event task.
     *
     * @param description Description of the event.
     * @param from The starting time of the event.
     * @param to The ending time of the event.
     */
    public Events(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the Event task.
     *
     * @return String representation of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}


