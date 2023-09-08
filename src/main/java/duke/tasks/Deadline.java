package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.Parser;

/**
 * Represents a task containing a description, and the due time.
 */
public class Deadline extends Task {
    private final LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        this(description, false, by);
    }

    /**
     * Creates a new {@code Deadline} instance
     *
     * @param description The description of the deadline.
     * @param isDone      The indication of the deadline being marked.
     * @param by          The due date of the deadline.
     */
    public Deadline(String description, boolean isDone, LocalDateTime by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String encode() {
        return String.format("D | %d | %s | %s", this.isDone ? 1 : 0, this.description,
                this.by.format(DateTimeFormatter.ofPattern(Parser.STORAGE_DATE_TIME_PATTERN)));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(
                DateTimeFormatter.ofPattern(Parser.OUTPUT_DATE_TIME_PATTERN)) + ")";
    }
}
