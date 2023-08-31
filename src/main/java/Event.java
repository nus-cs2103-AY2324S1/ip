public class Event extends Task {
    String start;
    String end;
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }
    @Override
    public String toString() {
        return "[E][" + this.getStatusIcon() + "] " + this.description
                + " (from: " + this.start + " to: " + this.end + ")";
    }
    public String toStorageString() {
        int stat = this.isDone ? 1 : 0;
        return "D|" + stat + "|" + this.description + "|" + this.start + "|" + this.end;
    }
}
