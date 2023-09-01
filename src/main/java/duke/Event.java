package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;
    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDateTime.parse(from);
        this.to = LocalDateTime.parse(to);
    }

    @Override
    public String printDesc() {
        String fromMessage =  this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy H:mm"));
        String toMessage =  this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy H:mm"));
        return "[E]" + super.printDesc() + " (from: " + fromMessage + " to: " + toMessage + ")";
    }

    @Override
    public String getDescription() {
        return "E~" + super.getDescription() + "~" + this.from + " - " + this.to;
    }
}
