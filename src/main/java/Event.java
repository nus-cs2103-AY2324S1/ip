import java.time.LocalDateTime;

public class Event extends Task {
    protected String from;
    protected String to;

    protected LocalDateTime fromDate;
    protected LocalDateTime toDate;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.fromDate = parseDateTime(from);
        this.to = to;
        this.toDate = parseDateTime(to);
    }

    @Override
    public String toString() {
        if (fromDate != null && toDate != null) {
            return getTask() + getStatusIcon() + " " + description + " (from: " + super.printDateTime(this.fromDate) + " to: " + super.printDateTime(this.toDate) + ")";
        } else {
            return getTask() + getStatusIcon() + " " + description + " (from: " + from + " to: " + to + ")";
        }
    }
}