package teho.main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate fromDate;
    private LocalDate toDate;
    public Event(String description, LocalDate fromDate, LocalDate toDate) {
        super(description);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.getStatusIcon() + " " + this.description
                + " (from: "
                + fromDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " to: "
                + toDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String fileString() {
        return "E|" + (this.isDone? 1: 0) + "|" + this.description + "|"
                + fromDate + "|" + toDate;
    }
}

