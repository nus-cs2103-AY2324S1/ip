public class Event extends Task {
    private String start;
    private String end;
    public Event(String start, String end, String description) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + this.start + " to: " + this.end + ")";
    }
}
