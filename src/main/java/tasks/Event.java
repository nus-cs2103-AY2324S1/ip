package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    public Event(String status, String description, String from, String to) {
        super(description);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
        if (status.equals("1")) {
            this.isDone = true;
        }
    }

    @Override
    public String toString() {
        String strFrom = this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String strTo = this.to.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return "[E]" + super.toString() + " (from: " + strFrom + " to: " + strTo + ")";
    }

    @Override
    public String toStringForFile() {
        return "E | " + super.toStringForFile() + " | " + this.from + " | " + this.to;
    }
}
