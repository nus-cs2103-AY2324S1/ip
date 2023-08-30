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
        String output = String.format("[E][%s] %s (from: %s to: %s)", super.getStatusIcon(), description, start, end);
        return output;
    }
}
