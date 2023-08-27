package robert.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import robert.exception.RobertException;

public class Event extends Task {
    private final LocalDate fromDate;
    private final LocalDate toDate;

    public Event(String description, LocalDate from, LocalDate to) throws RobertException {
        super(description);
        this.fromDate = from;
        this.toDate = to;

        if (this.fromDate.isAfter(this.toDate)) {
            throw new RobertException("The end date of the event is before the start date.\n"
                    + "Please set the dates such that the start date is before/on the end date.");
        }
    }

    public Event(String description, LocalDate from, LocalDate to, boolean isDone) throws RobertException {
        super(description, isDone);
        this.fromDate = from;
        this.toDate = to;

        if (this.fromDate.isAfter(this.toDate)) {
            throw new RobertException("The end date of the event is before the start date.\n"
                    + "Please set the dates such that the start date is before/on the end date.");
        }
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
