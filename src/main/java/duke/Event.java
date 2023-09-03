package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;

    public Event(String description, String start, String end) throws DukeException {
        super(description, TaskType.EVENT);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.start = LocalDateTime.parse(start, formatter);
        this.end = LocalDateTime.parse(end, formatter);

        if (description.trim().isEmpty()) {
            throw new DukeException("The description of an event cannot be empty.");
        }
        if (start.trim().isEmpty() || end.trim().isEmpty()) {
            throw new DukeException("The start or end time of an event cannot be empty.");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +
                " (from: " + start.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"))
                + " to: " + end.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) + ")";
    }
}