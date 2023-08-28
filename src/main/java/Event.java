/**
 * Represents an event.
 *
 * @author Pearlynn
 */

public class Event extends Task {

    /**
     * The start date/time of the event.
     */
    protected String from;

    /**
     * The end date/time of the event.
     */
    protected String to;

    /**
     * Constructor for Event class.
     *
     * @param description The description of the event.
     * @param from The start date/time of the event.
     * @param to The end date/time of the event.
     */
    public Event (String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the string representation of the event.
     *
     * @return A string representation of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
