package duke.task;

/**
 * Represents a EventsTask.
 */
public class EventsTask extends Task {
    private final String type = "E";
    private final String from;
    private final String to;

    /**
     * Constructor for EventsTask.
     * @param name Name of EventsTask.
     * @param from From of EventsTask.
     * @param to To of EventsTask.
     */
    public EventsTask(String name, boolean isDone, String from, String to) {
        super(name, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the string representation of EventsTask.
     * @return String representation of EventsTask.
     */
    @Override
    public String toString() {
        return String.format("[%s]%s (from: %s to: %s)",
                type, super.toString(), this.from, this.to);
    }

    /**
     * Returns the string representation of EventsTask to store.
     * @return String representation of EventsTask to store.
     */
    @Override
    public String toStringStore() {
        String mark = this.isDone ? "1" : "0";
        return String.format("%s,%s,%s,%s,%s", type, mark, this.name, this.from, this.to);
    }
}
