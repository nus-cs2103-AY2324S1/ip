package nexus.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class that inherits from Task.
 */
public class Deadline extends Task {

    protected LocalDateTime endDateTime;

    /**
     * Create deadline using description and datetime.
     *
     * @param description String.
     * @param endDateTime End date & time.
     */
    public Deadline(String description, LocalDateTime endDateTime) {
        super(description);
        this.endDateTime = endDateTime;
    }


    @Override
    public String toString() {
        String formattedBy = this.endDateTime.format(DateTimeFormatter.ofPattern("d MMM yyyy HHmm"));
        return "[D]" + super.toString() + " (by: " + formattedBy + ")";
    }

    @Override
    public String toStorageString() {
        String done = this.isDone ? "1" : "0";
        String endDateTimeString = this.endDateTime.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        return String.join("|","D", done, this.description, endDateTimeString);
    }
}

