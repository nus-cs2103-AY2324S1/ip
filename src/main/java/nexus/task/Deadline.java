package nexus.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class that inherits from Task.
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Create deadline using description and datetime.
     *
     * @param description String
     * @param by End date & time
     */
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
        return String.join("|","D", done, this.description, formattedBy);
    }
}

