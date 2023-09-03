import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime fromDateTime;
    protected LocalDateTime toDateTime;

    public Event(String description, LocalDateTime fromDateTime, LocalDateTime toDateTime) {
        super(description);
        this.fromDateTime = fromDateTime;
        this.toDateTime = toDateTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + 
                fromDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + " to: " + 
                toDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }

    @Override
    public String toDataString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + 
                fromDateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")) + "|" + 
                toDateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }
}
