package task;

import java.time.LocalDate;
import java.time.LocalTime;

public class Event extends Task {
    protected LocalDate startDate;
    protected LocalTime startTime;
    protected String start;
    protected LocalDate endDate;
    protected LocalTime endTime;
    protected String end;

    public Event(String description, LocalDate startDate, LocalTime startTime,
                 LocalDate endDate, LocalTime endTime) {
        super(description);
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
        this.start = startDate.toString() + " " + startTime.toString();
        this.end = endDate.toString() + " " + endDate.toString();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }
}
