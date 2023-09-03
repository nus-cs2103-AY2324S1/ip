package buddy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate start;
    protected LocalDate end;

    public Event(String description, String start, String end, boolean isDone) {
        super(description, isDone);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.start = LocalDate.parse(start, formatter);
        this.end = LocalDate.parse(end, formatter);
    }

    private String getDateString(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return date.format(formatter);
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    @Override
    public String toSaveFileFormat() {
        return String.format("%s | %d | %s | %s | %s",
                getTaskType(),
                isDone ? 1 : 0,
                this.description,
                this.getDateString(start),
                this.getDateString(end));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + getDateString(start)
                + " to: " + getDateString(end) + ")";
    }
}
