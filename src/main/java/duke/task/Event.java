package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private LocalDateTime start;
    private LocalDateTime end;
    public Event(String task, String start, String end, boolean done) {
        super(task, done);
        this.start = stringToDateObj(start);
        this.end = stringToDateObj(end);
    }

    private LocalDateTime stringToDateObj(String dateString) {
        return LocalDateTime.parse(dateString, formatter);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (from: %s, to: %s)", this.start.format(formatter), this.end.format(formatter));
    }

    @Override
    public String getTaskFileString() {
        return "E" + " , " + super.getTaskFileString() + " , " + this.start.format(formatter) + " , " + this.end.format(formatter);
    }
}
