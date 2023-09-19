package duke;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm", Locale.ENGLISH);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH);
        if (fromDateTime != null && toDateTime != null) {
            return " " + "[E]" + status + description +
                    " (from: " + fromDateTime.format(formatter) +
                    " to: " + toDateTime.format(formatter) + ")";
        } else {
            return " " + "[E]" + status + description +
                    " (from: " + fromDate.format(dateTimeFormatter) +
                    " to: " + toDate.format(dateTimeFormatter) + ")";
        }
    }
}
