import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class Event extends Task {
    protected LocalDateTime startTime;
    protected LocalDateTime endTime;

    public Event(String description, LocalDateTime startTime, LocalDateTime endTime) {
        super(description, "E");
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String toString() {
        return super.toString() + " (from: " + startTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"))
                + " to "
                + endTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }

    public String toFileString() {
        return super.toFileString() + " | " + startTime + " - " + endTime;
    }
}
