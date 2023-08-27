package robert.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDate fromDate;
    private final LocalDate toDate;

    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.fromDate = from;
        this.toDate = to;
    }

    public Event(String description, LocalDate from, LocalDate to, boolean isDone) {
        super(description, isDone);
        this.fromDate = from;
        this.toDate = to;
    }

    public LocalDate getFromDate() {
        return this.fromDate;
    }

    public LocalDate getToDate() {
        return this.toDate;
    }

    public boolean isHappeningOn(LocalDate date) {
        return !(this.toDate.isBefore(date) || this.fromDate.isAfter(date));
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)",
                super.toString(),
                this.fromDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")),
                this.toDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}
