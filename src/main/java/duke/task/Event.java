package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;

    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " +
                start.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")) +
                " to: " + end.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")) + ")";
    }

    @Override
    public String toSaveLine() {
        return String.format("E | %d | %s | %s | %s", isDone ? 1 : 0, description,
                start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }
}
