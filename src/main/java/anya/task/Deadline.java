package anya.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, String by) {
        super(description);
        this.by = super.convertStringToDate(by);
    }

    public Deadline(String description, String by, Boolean isDone) {
        super(description);
        this.by = super.convertStringToDate(by);
        if (isDone) {
            this.markAsDone();
        }
    }

    @Override
    public String getType() {
        return "D";
    }

    public String formatToSave() {
        return "D" + super.formatToSave() + " | " + this.by;
    }

    public String formatDate(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatDate(this.by) + ")";
    }
}
