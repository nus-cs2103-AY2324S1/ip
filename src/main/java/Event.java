/**
 * Event class that inherits from Task.
 * 
 * @var from Representing start time
 * @var to Representing end time
 * 
 * @author Owen Yeo
 */
public class Event extends Task{
    private String from;
    private String to;

    /**
     * Constructor for an event object.
     * 
     * @param label Descriptor for the event
     * @param from Start time
     * @param to End time
     */
    Event(String label, String from, String to) {
        super(label);
        this.from = from;
        this.to = to;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toSaveString() {
        return "E " + super.toSaveString() + " | " + from + " - " + to;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
