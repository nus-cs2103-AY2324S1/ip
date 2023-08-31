package duke.tasks;

import java.time.LocalDate;
import java.time.LocalTime;

public class Event extends Task {

    protected LocalDate fromDate;
    protected LocalTime fromTime;
    protected LocalDate toDate;
    protected LocalTime toTime;

    public Event(String description, boolean isDone, LocalDate fromDate, LocalTime fromTime, LocalDate toDate,
            LocalTime toTime) {
        super(description, isDone);
        this.fromDate = fromDate;
        this.fromTime = fromTime;
        this.toDate = toDate;
        this.toTime = toTime;
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (from: %s %s to: %s %s)", getStatusIcon(), description,
                (fromDate != null ? fromDate : ""), (fromTime != null ? fromTime : ""),
                (toDate != null ? toDate : ""), (toTime != null ? toTime : ""));
    }

    @Override
    public String toFileString() {
        return String.format("E # %d # %s # %s %s # %s %s", (isDone ? 1 : 0), description,
                (fromDate != null ? fromDate : ""), (fromTime != null ? fromTime : ""),
                (toDate != null ? toDate : ""), (toTime != null ? toTime : ""));
    }
}
