public class Event extends Task {
    private String from;
    private String to;
    Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }
    public String getType() {
        return "event";
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
