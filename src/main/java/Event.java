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
        // return "[E]" + (isDone ? "[X]" : "[ ]") + super.toString() + " (from: " + from + " to " + to + ")";
        return getTask() + getStatusIcon() + " " + description + " (from: " + from + " to: " + to + ")";
    }
}