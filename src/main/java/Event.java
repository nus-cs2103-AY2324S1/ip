public class Event extends Task {
    private String from;
    private String to;

    public Event(String description, String from, String to) {
        super(description.strip());
        this.from = from.strip();
        this.to = to.strip();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
