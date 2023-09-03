import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private LocalDateTime start;
    private LocalDateTime end;

    public Event(int status, String task, LocalDateTime start, LocalDateTime end) {
        super(status, task);
        this.start = start;
        this.end = end;
    }

    @Override
    public String convertTask() {
        return "E | " + super.getStatus() + " | " + super.getTask() +
                " | " + this.start.format(formatter) + " | " + this.end.format(formatter);
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " +
                this.start.format(formatter) + " to: " + this.end.format(formatter) + ")";
    }
}
