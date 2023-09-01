package Jarvis;

/**
 * Represents an event task with a start time and an end time.
 * <p>
 *     The Event class is a subclass of the Task class and has 2 additional attributes,
 *     from and to, the represent the start time and end time of the event.
 * </p>
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructs a new Event object.
     *
     * @param description The description of the event.
     * @param from The starting time of the event.
     * @param to The ending time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Converts the task to a user-friendly string representation.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
