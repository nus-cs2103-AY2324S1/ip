package extensions;
/**
 * Represents a task with starting and ending dates/times.
 */
public class Event extends Task {
    String from;
    String to;
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }
    @Override
    public String getSaveFormat() {
        return String.format("E | %c | %s | %s | %s",
                this.getDoneSymbol(),
                this.description,
                this.from,
                this.to);
    }
    @Override
    public String toString() {
        return String.format("[E][%c] %s (from: %s, to: %s)",
                this.getDoneSymbol(),
                this.description,
                this.from,
                this.to);
    }

}