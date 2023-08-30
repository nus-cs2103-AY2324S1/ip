public class Event extends Task {
    private String from;
    private String to;

    // Constructor for Event
    public Event(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    // Constructor for Deadline with done status
    public Event(String name, String from, String to, boolean isDone) {
        super(name, isDone);
        this.from = from;
        this.to = to;
    }

    // Get string representation of the Event
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    // Gets the start of the Event
    public String getFrom() {
        return this.from;
    }

    // Gets the end of the Event
    public String getTo() {
        return this.to;
    }
}