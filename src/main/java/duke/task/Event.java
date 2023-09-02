package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Event extends Task {
    public LocalDate begin;
    public LocalDate end;

    public Event(String name, LocalDate begin, LocalDate end, String isDone) {
        super(name, isDone);
        this.begin = begin;
        this.end = end;
    }

    @Override
    public String toDataString() {
        return super.toDataString() + " | " + begin.format(DateTimeFormatter.ISO_LOCAL_DATE) +
                " | " + end.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    @Override
    public String toString() {
        String str = "[E] " + super.getStatus() + " " + super.name + " (from: " +
                begin.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " to: " +
                end.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
        return str;
    }
}
