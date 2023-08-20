package Tasks;

/**
 * An Event is a task that will take place at a specific date and time. Therefore, it must keep track
 * of when the event is and from when to when the event will occur.
 */
public class Event extends Task {

    private String from;
    private String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.from, this.to);
    }
}
