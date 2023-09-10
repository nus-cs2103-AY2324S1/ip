import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    public Event(String description, LocalDateTime fromDateTime, LocalDateTime toDateTime) {
        super(description, fromDateTime);
        this.dateTime = toDateTime;
    }

    @Override
    public String toString() {
        String status = "[" + getStatusIcon() + "] ";
        return " " + "[E]" + status + description +
                " (from: " + dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) +
                " to: " + dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }
}
