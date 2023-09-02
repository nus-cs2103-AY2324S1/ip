package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    LocalDateTime dateTime;
    public Deadline(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }
    public String getDateTimeFormat() {
        return this.dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy h:mm a"));
    }
    @Override
    public String getSaveFormat() {
        return String.format("D | %c | %s | %s",
                this.getDoneSymbol(),
                this.description,
                this.getDateTimeFormat());
    }
    @Override
    public String toString() {
        return String.format("[D][%c] %s (by: %s)",
                this.getDoneSymbol(),
                this.description,
                this.getDateTimeFormat());
    }
}