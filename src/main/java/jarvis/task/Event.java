package jarvis.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description, false);
        this.from = from;
        this.to = to;
    }

    public Event(String description, Boolean isDone, LocalDateTime from, LocalDateTime to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    public String getFromString() {
        return this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"));
    }

    public String getToString() {
        return this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"));
    }

    @Override
    public String toSaveString() {
        return "E | " + super.toSaveString() + " | " + getFromString() + " | " + getToString();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + getFromString() + " to: " + getToString() + ")";
    }
}
