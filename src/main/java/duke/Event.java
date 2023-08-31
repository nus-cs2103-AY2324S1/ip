package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = parseDateTime(from);
        this.to = parseDateTime(to);
    }

    private LocalDateTime parseDateTime(String by) {
        DateTimeFormatter[] formatters = {
                DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
                DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),
                DateTimeFormatter.ISO_LOCAL_DATE_TIME
        };

        for (DateTimeFormatter formatter : formatters) {
            try {
                return LocalDateTime.parse(by, formatter);
            } catch (DateTimeParseException e) {

            }
        }
        throw new DateTimeParseException("No suitable date-time format found", by, 0);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        return "[E]" + super.toString() + " (from: " + this.from.format(formatter) + " to: "
                + this.to.format(formatter) + ")";
    }

    @Override
    public String toFile() {
        return "E | " + super.toFile() + " | " + this.from + " | " + this.to;
    }
}
