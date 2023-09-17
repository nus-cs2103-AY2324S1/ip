package tasket.task;

/**
 * The class for event.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * The constructor of event.
     * @param description
     * @param from
     * @param to
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * @inheritDocs
     *
     * @return The string format of the task.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.from, this.to);
    }

    /**
     * @inheritDocs
     *
     * @return The save format of the task.
     */
    @Override
    public String toSaveString() {
        return String.format("E | %s | %s | %s", super.toSaveString(), this.from, this.to);
    }
}
