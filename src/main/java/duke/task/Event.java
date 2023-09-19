package duke.task;

/**
 * Represents an event task that has a description, a start date/time, and an end date/time.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructor with description, start date/time, and end date/time.
     *
     * @param description The description of the event.
     * @param from The start date/time of the event.
     * @param to The end date/time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the start date/time of the event.
     *
     * @return The start date/time.
     */
    public String getFrom() {
        return this.from;
    }

    /**
     * Returns the end date/time of the event.
     *
     * @return The end date/time.
     */
    public String getTo() {
        return this.to;
    }

    /**
     * Returns a string representation of the duke.task.Event object.
     *
     * @return A string representation of the duke.task.Event object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

}
