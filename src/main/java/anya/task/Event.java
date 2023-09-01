package anya.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = super.convertStringToDate(from);
        this.to = super.convertStringToDate(to);
    }

    public Event(String description, String from, String to, Boolean isDone) {
        super(description);
        this.from = super.convertStringToDate(from);
        this.to = super.convertStringToDate(to);
        if (isDone) {
            this.markAsDone();
        }
    }

    @Override
    public String getType() {
        return "E";
    }

    public String formatToSave() {
        return "E" + super.formatToSave() + " | " + this.from + " | " + this.to;
    }

    public String formatDate(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + formatDate(this.from) + " to: " + formatDate(this.to) + ")";
    }
}
