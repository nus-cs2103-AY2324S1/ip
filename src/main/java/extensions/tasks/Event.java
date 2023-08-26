package extensions.tasks;

/**
 * A Task that starts at a specific date/time and ends at a specific date/time.
 */
public class Event extends Task {

    // The start and end date/time of the Event task.
    protected final String start;
    protected final String end;

    /**
     * Constructor for a Deadline task.
     *
     * @param description The description of the Deadline task.
     * @param start The start date/time of the Event task.
     * @param end The end date/time of the Event task.
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the String representation of the Deadline task.
     *
     * @return String representation of the Deadline task.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.start, this.end);
    }

    /**
     * Exports the Event task to a String to be saved.
     *
     * @return String representation of the Event task to be saved.
     */
    @Override
    public String export() {
        return String.format("EVENT || %s || %s || %s || %s", super.export(), "", this.start, this.end);
    }
}
