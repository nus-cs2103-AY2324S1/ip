package task;

/**
 * Represents a task that starts at a specific date/time and ends at a specific date/time.
 *
 * @author Andrew Daniel Janong
 */
public class Event extends Task {
    /**
     * The start time of the event.
     */
    protected String startTime;

    /**
     * The end time of the event.
     */
    protected String endTime;

    /**
     * A public constructor for the task.Event.
     * @param description
     * @param startTime
     * @param endTime
     */
    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toDataRepresentation() {
        return "E|" + super.toDataRepresentation() + "|" + startTime + "|" + endTime;
    }


    /**
     * A string representation of a task.Event.
     * Uses an extra [E] to represent a task.Event, start time, and end time.
     * @return the string representation of the task.Event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startTime + " to: " + this.endTime + ")";
    }
}
