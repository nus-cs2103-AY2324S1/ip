public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, boolean isDone, String from, String to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    public String toFileString() {
        // Convert task to file format string
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from + "-" + to;
    }

}
