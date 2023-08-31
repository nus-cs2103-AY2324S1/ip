import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    LocalDateTime start;
    LocalDateTime end;
    DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM, HH:mm");
    DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }
    @Override
    public String toString() {
        return "[E][" + this.getStatusIcon() + "] " + this.description
                + " (from: " + this.start.format(outputFormatter) + " to: " + this.end.format(outputFormatter) + ")";
    }
    public String toStorageString() {
        int stat = this.isDone ? 1 : 0;
        return "E|" + stat + "|" + this.description + "|"
                + this.start.format(inputFormatter) + "|" + this.end.format(inputFormatter);
    }
}
