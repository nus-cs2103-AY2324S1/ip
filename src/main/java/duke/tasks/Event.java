package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate start;
    protected LocalDate end;

    public Event(String description, String start, String end) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.start = LocalDate.parse(start, formatter);
        this.end = LocalDate.parse(end, formatter);
    }

    public Event(String description, String start, String end, boolean other) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd yyyy");
        this.start = LocalDate.parse(start, formatter);
        this.end = LocalDate.parse(end, formatter);
    }

    private String getDateString(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd yyyy");
        return date.format(formatter);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.getDateString(start) +
                " to: " + this.getDateString(end) + ")";
    }
}

