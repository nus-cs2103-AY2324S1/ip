/**
 * Encapsulates an Event task that can be added to the task manager.
 *
 * @author Teo Kai Sheng
 */
public class Event extends Task {

    /**
     * Starting date of the event.
     */
    protected String from;

    /**
     * Ending date of the event.
     */
    protected String to;

    /**
     * Constructor to create an Event.
     * @param description Description of the event.
     * @param from Starting date of the event.
     * @param to Ending date of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the String representation of the event.
     * @return A String representing the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to:" + to + ")";
    }
}