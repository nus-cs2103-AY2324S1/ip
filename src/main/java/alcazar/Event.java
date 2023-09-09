package alcazar;

/**
 * Class to define an Event Task
 */
public class Event extends Task {

    protected String from;
    protected String to;

    /**
     * Constructor for an Event
     * @param description The description of an event
     * @param from Start date/time of Event
     * @param to End date/time of Event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Used to evaluate String form of an Event
     * @return String form of an Event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to.trim() + ")";
    }
}
