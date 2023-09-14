package ekud.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    // dateTime object associated with this deadline
    LocalDateTime dateTime;

    public Deadline(String description, LocalDateTime dateTime, Priority priority) {
        super(description, priority);
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
        return String.format("D | %c | %s | %s | %s",
                this.getDoneSymbol(),
                this.description,
                this.getDateTimeFormat(),
                this.getPriority());
    }

    /**
     * String representation of this deadline as a task.
     * @return String
     */
    @Override
    public String toString() {
        return String.format("[D][%c] %s (by: %s) (%s priority)",
                this.getDoneSymbol(),
                this.description,
                this.getDateTimeFormat(),
                this.getPriority());
    }
}