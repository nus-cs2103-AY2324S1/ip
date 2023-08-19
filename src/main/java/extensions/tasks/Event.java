package extensions.tasks;

/**
 * A Task that starts at a specific date/time and ends at a specific date/time.
 */
public class Event extends Task {

    // The start and end date/time of the Event task.
    protected String start;
    protected String end;

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
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }
}
