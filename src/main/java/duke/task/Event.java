package duke.task;

import java.time.LocalDateTime;

public class Event extends Task {

    protected LocalDateTime start;
    protected LocalDateTime end;

    public Event (String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + "(from: " + this.start + " to: " + this.end + ")";
    }
}
