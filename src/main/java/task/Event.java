package task;

public class Event extends Task {
    protected String startDateTime;
    protected String endDateTime;
    public Event(String description, String startDateTime, String endDateTime) {
        super(description);
        this.startDateTime = DateFormatter.format(startDateTime, "MMM d yyyy");
        this.endDateTime = DateFormatter.format(endDateTime, "MMM d yyyy");
    }

    public Event(String description, String startDateTime, String endDateTime, boolean isDone) {
        super(description, isDone);
        this.startDateTime = DateFormatter.format(startDateTime, "MMM d yyyy");
        this.endDateTime = DateFormatter.format(endDateTime, "MMM d yyyy");
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s, to: %s)", super.toString(), this.startDateTime, this.endDateTime);
    }

    @Override
    public String convertToFileFormat() {
        return String.format("E|%s|%s|%s", super.convertToFileFormat(), this.startDateTime, this.endDateTime);
    }
}
