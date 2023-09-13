import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime fromDateTime;
    protected LocalDate fromDate;
    protected LocalDateTime toDateTime;
    protected LocalDate toDate;

    public Event(String description, LocalDateTime fromDateTime, LocalDateTime toDateTime) {
        super(description);
        this.fromDateTime = fromDateTime;
        this.toDateTime = toDateTime;
    }
    public Event(String description, LocalDate fromDate, LocalDate toDate) {
        super(description);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }
    @Override
    public String toString() {
        String status = "[" + getStatusIcon() + "] ";
        if (fromDateTime != null && toDateTime != null) {
            return " " + "[E]" + status + description +
                    " (from: " + fromDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) +
                    " to: " + toDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
        } else {
            return " " + "[E]" + status + description +
                    " (from: " + fromDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) +
                    " to: " + toDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
        }
    }
}
