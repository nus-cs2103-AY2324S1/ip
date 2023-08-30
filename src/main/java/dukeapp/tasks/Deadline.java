package dukeapp.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task containing a description, and the due time.
 */
public class Deadline extends Task {
    private final LocalDateTime by;

    protected Deadline(String description, LocalDateTime by) {
        this(description, false, by);
    }

    protected Deadline(String description, boolean isDone, LocalDateTime by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String encode() {
        return String.format("D | %d | %s | %s", this.isDone ? 1 : 0,
                this.description,
                this.by.format(DateTimeFormatter.ofPattern(STORAGE_DATE_TIME_PATTERN)));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(
                DateTimeFormatter.ofPattern(OUTPUT_DATE_TIME_PATTERN)) + ")";
    }
}