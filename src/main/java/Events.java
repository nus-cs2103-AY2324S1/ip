/**
 * A class for Events tasks.
 */
public class Events extends Task{
    /** Class field by that tells when the event is from. */
    protected String from;
    /** Class field by that tells until when the event is done. */
    protected String to;

    /**
     * Constructor to initialize the Events class.
     * @param description Describes the event.
     * @param from When the event is from.
     * @param to Until when the event is to.
     */
    public Events(String description, String from,  String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Method that overrides default toString.
     * @return String representation of Events.
     */
    @Override
    public String toString() {
        return String.format("[E][%s] %s (from: %s to: %s)", this.getStatusIcon(), this.description, this.from, this.to);
    }
}
