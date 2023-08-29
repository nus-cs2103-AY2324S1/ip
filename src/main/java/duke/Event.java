package duke;

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
        return "[E]" + super.toString() + " (from: " + convertDateToString(this.from)
                + " to: " + convertDateToString(this.to) + ")";
    }

    @Override
    public String lineToWriteFile() {
        return "E | " + super.lineToWriteFile() + " | " + convertDateToString(this.from)
                + " to " + convertDateToString(this.to);
    }

    public String convertDateToString(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma"));
    }
}