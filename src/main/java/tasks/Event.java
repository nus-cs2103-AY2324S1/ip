package tasks;

public class Event extends Task {
    private String start;
    private String end;

    protected Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +
                String.format("from: %s to: %s", start, end);
    }
}
