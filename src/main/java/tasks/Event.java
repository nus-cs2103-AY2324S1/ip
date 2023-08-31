package tasks;

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
        String newStart =  start.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
        String newEnd =  end.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
        return "[E]" + super.toString() + " (from: " + newStart + " to: " + newEnd + ")";
    }
}
