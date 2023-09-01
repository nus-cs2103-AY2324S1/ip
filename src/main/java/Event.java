/**
 * Represent a task that start at a specific date/time and ends at a specific date/time.
 */
public class Event extends Task {
    protected String start;
    protected String end;

    /**
     * Creates a event task that is initially undone.
     *
     * @param description The description of the task that the user inputs
     * @param start The start time that the user inputs
     * @param end The ending time that the user inputs
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toSaveFormat() {
        return "E | " + super.toSaveFormat() + " | " + this.start + "-" + this.end;
    }

    @Override
    public String toString() {
        return "[E][" + this.getStatusIcon() + "] " + this.description +
                " (from: " + this.start + " to: " + this.end + ")";
    }
}
