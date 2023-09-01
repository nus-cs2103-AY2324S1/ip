package duke.data.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
public class Event extends Task {
    private String start;
    private String end;

    /**
     * Constructor to initialize Event.
     *
     * @param description Description of the event.
     * @param start Start date of event.
     * @param end End date of event.
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
        try {
            LocalDate d1 = LocalDate.parse(start);
            LocalDate d2 = LocalDate.parse(end);
            this.start = d1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            this.end = d2.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e) {
            this.start = start;
            this.end = end;
        }


    }
    @Override
    public  String saveString() {
        return "E" + super.saveString() + " | " + this.start + "-" + this.end;
    }
    @Override
    public String toString() {
        return String.format("[E] %s (from: %s to: %s)", super.toString(), this.start, this.end);
    }
}