public class Event extends Task {
    private final String from;
    private final String to;

    public Event(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.from, this.to);
    }

    @Override
    public String toFormat() {
        return String.format("E|%s|%s|%s|%s", super.getName(), super.isDone() ? "X" : " ", this.from, this.to);
    }
}
