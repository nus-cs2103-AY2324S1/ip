public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        setFrom(from);
        setTo(to);
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public String toSaveDataFormat() {
        return String.format("E | %d | %s | %s | %s", isDone() ? 1 : 0, getDescription(), getFrom(), getTo());
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), getFrom(), getTo());
    }
}
