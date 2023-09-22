package tasks;

import java.time.LocalDateTime;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;
    public Event(String description, String from, String to) {
        super(description);
        this.from = stringToDate(from);
        this.to = stringToDate(to);
    }

    public LocalDateTime getFrom() {
        return this.from;
    }

    public LocalDateTime getTo() {
        return this.to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + dateToString(from) + " to: " + dateToString(to) + ")";
    }
}
