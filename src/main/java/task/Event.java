package task;

public class Event extends Task {
    protected String from;
    protected String to;

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
