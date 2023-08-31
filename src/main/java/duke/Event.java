package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDate start;
    private final LocalDate end;
    public Event(String task, LocalDate start, LocalDate end) {
        super(task);
        this.start = start;
        this.end = end;
    }

    @Override
    public void mark() {
        this.done = true;
        System.out.println(super.line() + "Okay, I have marked this task as completed!" + "\n" + this.toString());
        System.out.println(super.line());
    }
    @Override
    public void unMark() {
        this.done = false;
        System.out.println(super.line() + "Okay, I have marked this task as incomplete!" + "\n" + this.toString());
        System.out.println(super.line());
    }
    @Override
    public String toString() {
        String checkbox = this.done ? "[X] " : "[ ] ";
        String startTime = "(from: " + start.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String endTime = "to: " + end.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        return "[E]" + checkbox + task + " " + startTime + " " + endTime;
    }
}
