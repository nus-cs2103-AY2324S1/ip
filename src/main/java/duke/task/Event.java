package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }

    @Override
    public String writeFileString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + ")";
    }
}
