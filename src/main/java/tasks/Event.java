package tasks;

import parser.Parser;

import java.time.LocalDateTime;

public class Event extends Task {
    private Dateable start;
    private Dateable end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = Dateable.of(start);
        this.end = Dateable.of(end);
    }

    public Event(String description, String start, String end, boolean completed) {
        super(description, completed);
        this.start = Dateable.of(start);
        this.end = Dateable.of(end);
    }

    @Override
    public String getFileFormat() {
        return String.format("E | %s | %s | %s", super.getFileFormat(),
                Parser.parseDisplayDatetimeToStorageDatetime(this.start.toString()),
                Parser.parseDisplayDatetimeToStorageDatetime(this.end.toString()));
    }

    @Override
    public boolean isOnDate(LocalDateTime startOfDay, LocalDateTime endOfDay) {
        // Event can either start or end on the date itself, or both
        // Or it can surround the date
        return (this.start.isAfterOrOn(startOfDay) && this.start.isBeforeOrOn(endOfDay))
                || (this.end.isAfterOrOn(startOfDay) && this.end.isBeforeOrOn(endOfDay))
                || (this.start.isBeforeOrOn(startOfDay) && this.end.isAfterOrOn(endOfDay));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +
                String.format(" (from: %s to: %s)", start, end);
    }
}
