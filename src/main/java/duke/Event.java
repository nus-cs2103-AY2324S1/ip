package duke;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

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
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMM yyyy");
        String startDate = dateFormatter.format(this.start.toLocalDate());
        String endDate = dateFormatter.format(this.start.toLocalDate());
        String startTime = this.start.toLocalTime().toString();
        String endTime = this.end.toLocalTime().toString();
        return "[E]" + super.toString() + String.format(
                " (from: %s to: %s)",
                startDate + " " + startTime,
                endDate + " " + endTime);
    }
}
