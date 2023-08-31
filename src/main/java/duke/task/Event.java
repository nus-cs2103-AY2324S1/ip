package duke.task;

import duke.parse.DateTimeManager;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    public Event(String name, LocalDateTime start, LocalDateTime end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    @Override
    public String data() {
        return "E " + super.data()
                + " /from " + DateTimeManager.dateToStringData(this.start)
                + " /to " + DateTimeManager.dateToStringData(this.end);
    }

    @Override
    public boolean containsDate(LocalDate date) {
        return (this.start.toLocalDate().isBefore(date) || this.start.toLocalDate().equals(date))
                && (this.end.toLocalDate().isAfter(date) || this.end.toLocalDate().equals(date));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + DateTimeManager.dateToDisplay(this.start)
                + " to: " + DateTimeManager.dateToDisplay(this.end) + ")";
    }
}
