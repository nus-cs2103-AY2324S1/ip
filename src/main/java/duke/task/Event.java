package duke.task;

/**
 * The Event class represents a task with a description,
 * completion status, and a start and end period.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructs an Event object.
     *
     * @param description Description of the task.
     * @param from Start period of the event.
     * @param to End period of the event.
     */
    public Event(String description, String from, String to) {
        super(description);

        assert from != null : "From cannot be null";
        assert to != null : "To cannot be null";

        this.from = from.trim();
        this.to = to.trim();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString().trim() + " (from " + this.from + " to " + this.to + ")";
    }

    @Override
    public String toFileFormat() {
        return "E" + super.toFileFormat() + " | from " + this.from + " | to " + this.to + "\n";
    }
}
