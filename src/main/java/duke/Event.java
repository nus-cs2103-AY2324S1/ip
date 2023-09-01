package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime start;
    protected LocalDateTime end;
    public Event(String details, LocalDateTime start, LocalDateTime end) {
        super(details);
        this.start = start;
        this.end = end;
    }

    public Event(String details, boolean isCompleted, LocalDateTime start,
                 LocalDateTime end) {
        super(details, isCompleted);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        String startDate = this.start.toLocalDate().format(dateFormatter);
        String endDate = this.end.toLocalDate().format(dateFormatter);
        String startTime = this.start.toLocalTime().format(timeFormatter);
        String endTime = this.end.toLocalTime().format(timeFormatter);
        return "[E]" + super.toString() + String.format(
                " (from: %s to: %s)",
                startDate + " " + startTime,
                endDate + " " + endTime);
    }
}
