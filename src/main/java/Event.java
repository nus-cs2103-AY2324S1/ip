public class Event extends Task {
    protected String start, end;
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public Event(String description, boolean isDone, String start, String end) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E][" + this.getStatusIcon() + "] " +  this.description + " (from: " + this.start + " to: " + this.end + ")";
    }

    @Override
    public String toSaveFormat() {
        return String.format("E,%s,%s,%s,%s", description, isDone ? "1" : "0", start, end);
    }
}
