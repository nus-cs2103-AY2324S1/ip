package tasks;

public class Event extends Task {
    private Dateable start;
    private Dateable end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = Dateable.of(start);
        this.end = Dateable.of(end);
    }

    public Event(String description, String start, String end, boolean completed) {
        super(description, completed);
        this.start = Dateable.of(start);
        this.end = Dateable.of(end);
    }

    @Override
    public String getFileFormat() {
        return String.format("E | %s | %s | %s", super.getFileFormat(), this.start, this.end);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +
                String.format(" (from: %s to: %s)", start, end);
    }
}
