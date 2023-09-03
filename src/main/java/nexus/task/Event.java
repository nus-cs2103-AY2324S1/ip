package nexus.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d MMM yyyy HHmm");
        String formattedFrom = this.from.format(dateTimeFormatter);
        String formattedTo = this.to.format(dateTimeFormatter);
        return "[E]" + super.toString() + " (from: " + formattedFrom + " to: " + formattedTo + ")";
    }

    @Override
    public String toStorageString() {
        String done = this.isDone ? "1" : "0";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        String formattedFrom = this.from.format(dateTimeFormatter);
        String formattedTo = this.to.format(dateTimeFormatter);
        return String.join("|", "E", done, this.description, formattedFrom, formattedTo);
    }
}
