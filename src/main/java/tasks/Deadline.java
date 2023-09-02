package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    // dateTime object associated with this deadline
    LocalDateTime dateTime;

    public Deadline(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    /**
     * Returns the user-friendly string format of this deadline's dateTime object.
     * @return String
     */
    public String getDateTimeFormat() {
        return this.dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy h:mm a"));
    }

    /**
     * Returns the compact saved format for this deadline.
     * @return String
     */
    @Override
    public String getSaveFormat() {
        return String.format("D | %c | %s | %s",
                this.getDoneSymbol(),
                this.description,
                this.getDateTimeFormat());
    }

    /**
     * String representation of this deadline as a task.
     * @return String
     */
    @Override
    public String toString() {
        return String.format("[D][%c] %s (by: %s)",
                this.getDoneSymbol(),
                this.description,
                this.getDateTimeFormat());
    }
}