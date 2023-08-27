import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;

    public Event(String task, LocalDateTime start, LocalDateTime end) {
        super(task);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return String.format("[E] " + super.toString() + " (from: "
                + start.format(DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm")) + ", to: "
                + end.format(DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm")) + ")");
    }
}
