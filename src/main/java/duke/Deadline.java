package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy");
    protected LocalDate by;
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }
    public LocalDate getDeadline() {
        return this.by;
    }

    @Override
    public String toString() {
        return "[D]" + "[" + this.getStatusIcon() + "]"
                + super.toString() + " (by: " + by.format(DATE_FORMATTER) + ")";
    }
}
