package duke;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Event extends Task {

    protected String from;
    protected String to;
    protected LocalDateTime fromDateTime;
    protected LocalDateTime toDateTime;

    public Event(String description, String from, String to, boolean isDone) {
        super(description, isDone);
        this.fromDateTime = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        this.toDateTime = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        this.from = fromDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm"));
        this.to = toDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}