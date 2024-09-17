package alcazar.tasks;

/**
 * Encapsulates an Event Task
 */
public class Event extends Task {

    /** The start time of this event */
    protected String fromTiming;
    /** The end time of this event */
    protected String toTiming;

    /**
     * Constructs an Event
     * @param description The description of an event
     * @param fromTiming Start date/time of Event
     * @param toTiming End date/time of Event.
     */
    public Event(String description, String fromTiming, String toTiming) {
        super(description);
        this.fromTiming = fromTiming;
        this.toTiming = toTiming;
    }

    /**
     * Evaluates String form of an Event
     * @return String form of an Event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + fromTiming + " to: " + toTiming.trim() + ")";
    }
}