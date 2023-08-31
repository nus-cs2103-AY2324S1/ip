package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;
    protected String byDate;

    public Deadline(String description, String by) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.by = LocalDate.parse(by, formatter);
        DateTimeFormatter byFormatter = DateTimeFormatter.ofPattern("MMMM dd yyyy");
        this.byDate = this.by.format(byFormatter);
    }

    public Deadline(String description, String by, Boolean other) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d yyyy");
        this.by = LocalDate.parse(by, formatter);
        this.byDate = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.byDate + ")";
    }
}

