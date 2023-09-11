package dude.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }
    @Override
    public String getType() {
        return "event";
    }
    @Override
    public String saveTask() {
        String data = "E | ";
        if (this.isDone()) {
            data += "1 | ";
        } else {
            data += "0 | ";
        }
        data += this.getDescription();
        data = data + " | " + this.from + " | " + this.to + "\n";
        return data;
    }
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a", Locale.ENGLISH);
        String fromFormatted = from.format(formatter);
        String toFormatted = to.format(formatter);
        return "[E]" + super.toString() + " (from: " + fromFormatted + " to: " + toFormatted + ")";
    }
}
