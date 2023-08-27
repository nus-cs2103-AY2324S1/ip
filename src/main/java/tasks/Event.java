package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);

        try {
            LocalDate date = LocalDate.parse(from);
            this.from = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (DateTimeParseException e) {
            this.from = from;
        }

        try {
            LocalDate date = LocalDate.parse(to);
            this.to = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (DateTimeParseException e) {
            this.to = to;
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +
                " (from: " + this.from +
                " to: " + this.to + ")";
    }
}
