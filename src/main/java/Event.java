import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, String fromDateString, String fromTimeString,
                 String toDateString, String toTimeString) {
        super(description);

        LocalDate fromDate = LocalDate.parse(fromDateString);
        LocalTime fromTime = LocalTime.parse(fromTimeString);
        LocalDateTime from = LocalDateTime.of(fromDate, fromTime);

        LocalDate toDate = LocalDate.parse(toDateString);
        LocalTime toTime = LocalTime.parse(toTimeString);
        LocalDateTime to = LocalDateTime.of(toDate, toTime);

        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy, HH:mm");
        String fromFormatted = this.from.format(formatter);
        String toFormatted = this.to.format(formatter);
        return "[E]" + super.toString() + " (from: " + fromFormatted + " to: " + toFormatted + ")";
    }
}
