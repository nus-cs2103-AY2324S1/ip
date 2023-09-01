package duke.tasks;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String description, LocalDateTime from, LocalDateTime to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        String outputFrom = from.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a"));
        String outputTo = to.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a"));

        return "[E]" + super.toString() + " (from: " + outputFrom + " to: " + outputTo + ")";
    }

    @Override
    public String write() {
        String outputFrom = from.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        String outputTo = to.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));

        return "E | " + super.write() + " | " + outputFrom + " | " + outputTo;
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public LocalDateTime getTo() {
        return to;
    }
}