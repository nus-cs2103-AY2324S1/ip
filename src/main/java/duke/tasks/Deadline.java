package duke.tasks;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        String output = by.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a"));
        return "[D]" + super.toString() + " (by: " + output + ")";
    }

    @Override
    public String write() {
        String output = by.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        return "D | " + super.write() + " | " + output;
    }

    public LocalDateTime getBy() {
        return by;
    }
}