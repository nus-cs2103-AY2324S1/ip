package duke.tasks;

import duke.Parser;

import java.time.LocalDateTime;

public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + Parser.formatDateTime(from) + " to: "
                + Parser.formatDateTime(to) + ")";
    }

    @Override
    public String toStorageFormat() {
        return ("E" + super.toStorageFormat() + " | " + from + " | " + to);
    }
}
