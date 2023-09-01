package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime start;
    protected LocalDateTime end;

    public Event(String description, boolean isDone, LocalDateTime start, LocalDateTime end) {

        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    public LocalDateTime getStart() {
        return this.start;
    }

    public LocalDateTime getEnd() {
        return this.end;
    }

    @Override
    public String contentLine() {
        return "E" + super.contentLine() + "/" + this.start.toString() + "/" + this.end.toString();
    }

    @Override
    public String toString() {

        String result = "[E]" + super.toString() + " (from: " + formatDate(this.start) + " to: " +
                formatDate(this.end) + ")";
        return result;
    }

    public String formatDate(LocalDateTime l) {
        return l.format(DateTimeFormatter.ofPattern("dd/MMM/yyyy HH:mm"));
    }
}
