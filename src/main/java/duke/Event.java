package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
    private DateTimeFormatter formatterSave = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public Event(String description, LocalDateTime start, LocalDateTime end, boolean isDone) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    public boolean isStartDateBefore (LocalDateTime start, LocalDateTime end) {
        if (start.isBefore(end) || start.equals(end)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toFileString() {
        return "E" + super.toFileString() + " | " + start.format(formatterSave)
                + " | " + end.format(formatterSave);
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() +
                " (from: " + start.format(formatter) + " to: " + end.format(formatter) + ")";
    }
}