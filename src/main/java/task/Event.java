package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Event extends Task {
    private LocalDateTime  start;
    private LocalDateTime  end;
    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");
        return String.format("[E][%s] %s (from: %s to: %s)", super.getStatusIcon(),
                description, start.format(formatter), end.format(formatter));
    }

    @Override
    public String toFileFormat(DateTimeFormatter formatter) {
        return String.format("E | %s | %s | %s | %s", super.isDoneString(), description, start, end);
    }
}
