package dan.task;

import dan.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    // fields
    protected LocalDate start;
    protected LocalDate end;

    // toString


    @Override
    public String toString() {
        return "[E]" + super.toString() +
                " (from: " + start.format(DateTimeFormatter.ofPattern("MMM d yyyy")) +
                " to: " + end.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String saveToString() {
        return "event," + super.saveToString() + "," + start + "," + end;
    }

    // Constructor
    public Event(String description, String start, String end) {
        super(description);
        this.start = LocalDate.parse(start);
        this.end = LocalDate.parse(end);
    }
    public Event(String description, int mark, String start, String end) {
        super(description, mark != 0);
        this.start = LocalDate.parse(start);
        this.end = LocalDate.parse(end);
    }
}
