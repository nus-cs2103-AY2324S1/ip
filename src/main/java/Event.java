/**
 * This class is a subclass of Task which defines an event task.
 */
public class Event extends Task {
    /** A String containing start of event. */
    private String from;
    /** A String containing end of event. */
    private String to;

    /**
     * A constructor of event
     * @param description Name of event
     * @param from Start of event
     * @param to End of event
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     *
     * @return String representation of event object
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from:" + this.from + "to:" + this.to + ")";
    }
}
