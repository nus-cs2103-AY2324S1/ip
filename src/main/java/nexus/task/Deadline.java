package nexus.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        String formattedBy = this.by.format(DateTimeFormatter.ofPattern("d MMM yyyy HHmm"));
        return "[D]" + super.toString() + " (by: " + formattedBy + ")";
    }

    @Override
    public String toStorageString() {
        String done = this.isDone ? "1" : "0";
        String formattedBy = this.by.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        return String.join("|","deadline", done, this.description, formattedBy);
    }
}

