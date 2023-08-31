package duke.task;

import java.time.LocalDate;

public class Event extends Task {
    protected LocalDate fromDate;
    protected LocalDate toDate;

    public Event(String description, LocalDate fromDate, LocalDate toDate) {
        super(description);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    @Override
    public String toString() {
        String status = "[" + (this.isDone ? "X" : " ") + "]";
        String duration = "(from: " + this.fromDate + " to: " + this.toDate + ")";
        return "[E]" + status + " " + this.description + " " + duration;
    }
}
