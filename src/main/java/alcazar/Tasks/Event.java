package alcazar.Tasks;

import alcazar.Tasks.Task;

/**
 * Class to define an Event Task
 */
public class Event extends Task {

    protected String fromTiming;
    protected String toTiming;

    /**
     * Constructor for an Event
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
     * Used to evaluate String form of an Event
     * @return String form of an Event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + fromTiming + " to: " + toTiming.trim() + ")";
    }
}
