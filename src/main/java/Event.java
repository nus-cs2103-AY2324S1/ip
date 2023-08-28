public class Event extends Task {
    protected String start;
    protected String end;

    public Event(String taskName, String start, String end) {
        super(taskName);
        this.start = start;
        this.end = end;
    }

    @Override
    public String getType() {
        return "T";
    }

    @Override
    public String getDescription() {
        return super.toString() + " | " + this.start + " | " + this.end;
    }

    @Override
    public String statusAndTask() {
        return "[E] " + super.toString() + " (from: " + this.start + " to: " + this.end + ")";
    }
}
