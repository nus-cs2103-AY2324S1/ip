package task;

public class Event extends Task {
    private String start;
    private String end;
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (from: %s to: %s)", super.getStatusIcon(), description, start, end);
    }

    @Override
    public String toFileFormat() {
        return String.format("E | %s | %s | %s | %s", super.isDoneString(), description, start, end);
    }
}
