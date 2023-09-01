package ip.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDate start;
    private final LocalDate end;

    public Event(String description, String start, String end) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.start = LocalDate.parse(start, formatter);
        this.end = LocalDate.parse(end, formatter);
    }

    @Override
    public String toFileFormat() {
        return "E | " + super.toFileFormat() + " | " + start + " | " + end;
    }

    public static Task fromFileFormat(String line) {
        String[] parts = line.split(" \\| ");
        Event event = new Event(parts[2], parts[3], parts[4]);
        if (parts[1].equals("1")) {
            event.markDone();
        }
        return event;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[E][" + (getIsDone() ? "X" : " ") + "] " + getDescription() + " (from: " + start.format(formatter) +
                " to: " + end.format(formatter) + ")";
    }
}