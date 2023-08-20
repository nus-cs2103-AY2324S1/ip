public class Event extends Task {
    // Fields
    String start;
    String end;

    //Constructor for Event
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return String.format("[E] %s (from: %s to: %s)", super.toString(), start, end);
    }
}
