package dukeapp.tasks;

/**
 * Represents a task containing the start and end time.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    protected Event(String description, String from, String to) {
        this(description, false, from, to);
    }

    protected Event(String description, boolean isDone, String from,
                    String to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String encode() {
        return String.format("E | %d | %s | %s-%s", this.isDone ? 1 : 0,
                this.description, this.from, this.to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +
                " (from: " + this.from + " to: " + this.to + ")";
    }
}
