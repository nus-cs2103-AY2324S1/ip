package didier.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * An didier.task.Event is a didier.task that will take place at a specific date and time. Therefore, it must keep track
 * of when the event is and from when to when the event will occur.
 */
public class Event extends Task {

    private LocalDate from;
    private LocalDate to;

    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String description, LocalDate from, LocalDate to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    @Override
    public String composeToFileString() {
        return String.format("E|%s|%s|%s", super.composeToFileString(), this.from.toString(), this.to.toString());
    }
}
