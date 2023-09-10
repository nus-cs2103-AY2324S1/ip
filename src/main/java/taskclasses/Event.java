package taskclasses;

import extensions.Tag;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected String from;
    protected String to;
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        this.to = to.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
    public Event(String description, boolean isDone, LocalDate from, LocalDate to, Tag tag) {
        super(description, isDone, tag);
        this.from = from.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        this.to = to.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
    @Override
    public String formatToFile() {
        return "E | " + super.formatToFile() + " | " + from + " - " + to;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
