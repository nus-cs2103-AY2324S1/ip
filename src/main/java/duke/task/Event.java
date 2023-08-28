package duke.task;

/**
 * Represents an event.
 */
public class Event extends Task {
    /** Represents when the event starts and ends. */
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String stringToFile() {
        return String.format("E | %s | %s | %s", super.stringToFile(), from, to);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), from, to);
    }

}
