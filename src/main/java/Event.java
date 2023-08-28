public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getStatusMessage() {
        return "[E]" + super.getStatusMessage() + " (from: " + this.from + " to: " + this.to + ")";
    }

    @Override
    public String toFormattedString() {
        return String.format("E | %s", super.toFormattedString());
    }
}
