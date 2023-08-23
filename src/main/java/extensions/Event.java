package extensions;
public class Event extends Task {
    String from;
    String to;
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }
    @Override
    public String toString() {
        return String.format("[E][%c] %s (from: %s, to: %s)",
                this.getDoneStatus(),
                this.description,
                this.from,
                this.to);
    }

}