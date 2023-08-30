public class Event extends Task {
    private String start;
    private String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public Event(String description, String start, String end, boolean isDone) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toFileString() {
        return "E" + super.toFileString() + " | " + start
                + " | " + end;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() +
                " (from: " + start + " to: " + end + ")";
    }
}