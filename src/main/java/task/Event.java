package task;

/**
 * Event task class
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructor for Event task class
     * @param description Description of task
     * @param from Start time
     * @param to End time
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[E]");
        sb.append("[" + getStatusIcon() + "] ");
        sb.append(description);
        sb.append(" (from: " + from + " to: " + to + ")");
        return sb.toString();
    }
}
