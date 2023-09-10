public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String description, String from, String to, boolean isDone) {
        super(description);
        this.from = from;
        this.to = to;
        this.isDone = isDone;
    }

    @Override
    public String parse() {
        return "E | " + this.description + " | " + this.isDone + " | " + this.from + " | " + this.to;
    }
    @Override
    public String toString() {
        return  "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
