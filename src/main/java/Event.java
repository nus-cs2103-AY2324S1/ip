public class Event extends Task{
    private String from;
    private String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return this.isDone ? "[E][X] " + this.description + " (from: " + from + " to: " + to + ")"
                : "[E][ ] "+ this.description + " (from: " + from + " to: " + to + ")";
    }
}
