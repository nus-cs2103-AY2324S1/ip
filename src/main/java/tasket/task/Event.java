package tasket.task;

/**
 * The class for event.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructs an event.
     *
     * @param description The description of event.
     * @param from The start time of event.
     * @param to The end time of event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs an event.
     * This version includes tags.
     *
     * @param description The description of event.
     * @param tags The tags of the event.
     * @param from The start time of event.
     * @param to The end time of event.
     */
    public Event(String description, String from, String to, String[] tags) {
        super(description, tags);
        this.from = from;
        this.to = to;
    }

    /**
     * {@inheritDoc}
     *
     * @return The string format of the task.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s) %s", super.toString(),
                        this.from, this.to, this.getTags())
                .trim();
    }

    /**
     * {@inheritDoc}
     *
     * @return The save format of the task.
     */
    @Override
    public String toSaveString() {
        return String.format("E | %s | %s | %s %s", super.toSaveString(),
                        this.from, this.to, this.getTags())
                .trim();
    }
}
