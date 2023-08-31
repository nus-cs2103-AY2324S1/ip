import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;
    public Event(LocalDateTime start, LocalDateTime end, String description) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM d yyyy ha");
        return "[E]" + super.toString() + " (from: " + this.start.format(format) + " to: " + this.end.format(format) + ")";
    }

    @Override
    public String toStringWithDateTime() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM d yyyy ha");
        return "[E]" + super.toString() + "DATETIME " + this.start.format(format) + " DATETIME_SPLIT " + this.end.format(format);
    }
}
