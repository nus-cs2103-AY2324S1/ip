package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
//    protected String by;
    protected LocalDateTime by;

    public Deadline(String description, String by) {
        super(description);
        this.by = parseDateTime(by);
//        this.by = by;
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
        return "[D]" + super.toString() + " (by: " + this.by.format(formatter) + ")";
    }

    @Override
    public String toFile() {
        return "D | " + super.toFile() + " | " + this.by;
    }
}
