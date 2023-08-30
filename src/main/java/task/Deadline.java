package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        String date = by.format(DateTimeFormatter.ofPattern("MMM d YYYY"));
        return "[D]" + super.toString() + " (by: " + date + ")";
    }

    @Override
    public String toFileString() {
        int bool = this.isDone ? 1 : 0;
        return "D | " + bool + " | " + this.description + " | " + by + "\n";
    }
}
