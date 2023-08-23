/**
 * Represents an event task that occurs within a specified time range.
 * This class extends the Task class and adds the event's start and end times.
 */
public class Event extends Task {
    /**
     * The start time of the event.
     */
    protected String from;
    /**
     * The end time of the event.
     */
    protected String to;

    /**
     * Constructs an Event object with the given description, start time, and end time.
     *
     * @param description The description of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the Event object.
     *
     * @return A formatted string including the task type, description, start time, and end time.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}