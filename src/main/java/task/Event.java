package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(  "EVENT",description);
        this.from = from;
        this.to = to;
    }

    public static Event fromFileString(String fileString) {
        String[] parts = fileString.split("\\|");
        String isCompleted = parts[1].trim();
        String taskName = parts[2].trim();
        String from = parts[3].trim();
        String to = parts[4].trim();

        Event event = new Event(taskName, parseDateTime(from), parseDateTime(to));

        if (isCompleted.equals("1")) {
            event.setCompleted();
        }

        return event;
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    @Override
    public String toString() {
        String fromFormat = from.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a"));
        String toFormat = to.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a"));
        return "[E] " + super.toString()
                + " (from: " + fromFormat
                + " to: " + toFormat
                + ")";
    }

    private static LocalDateTime parseDateTime(String dateTimeString) {
        return LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    @Override
    public String toFileString() {
        return TaskType.EVENT + " | " + (isCompleted() ? "1" : "0") + " | " + getDescription() + " | " + getFrom().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")) + " | " + getTo().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }
}
