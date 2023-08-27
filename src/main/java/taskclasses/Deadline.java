package main.java.taskclasses;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
    public Deadline(String description, boolean isDone, LocalDate by) {
        super(description, isDone);
        this.by = by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
    @Override
    public String formatToFile() {
        return "D | " + super.formatToFile() + " | " + by;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
