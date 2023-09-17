package martin.task;

import java.time.Duration;
import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public void snooze(Duration duration) {
        this.from = this.from.plus(duration);
        this.to = this.to.plus(duration);
    }

    @Override
    public String toString() {
        return "[E][" + (isDone ? "X" : " ") + "] " + description + " (from: " + from + " to: " + to + ")";
    }
}