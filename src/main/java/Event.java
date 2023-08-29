public class Event extends Task {
    private final String to;
    private final String from;

    public Event(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from:" + this.from + " to: " + this.to + ")";
    }

    public String convertTaskToString() {
        return "E | " + (super.isDone() ? "1" : "0") + " | " + super.getName() + " | " + this.from + " | " + this.to;
    }
}
