public class Event extends Task {
    private String start;
    private String end;
    public Event(String description, String start, String end) throws SpotException {
        super(description);
        this.start = start;
        this.end = end;
    }

    public Event(String description, boolean isDone, String start, String end) throws SpotException {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }

    @Override
    public String toLine() {
        return " E | " + super.toLine() + " | " + this.start + " | " + this.end;
    }
}
