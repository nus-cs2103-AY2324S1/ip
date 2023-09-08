package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.Parser;


/**
 * Represents a task containing the start and end time.
 */
public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        this(description, false, from, to);
    }

    /**
     * Creates a new {@code Event} instance
     *
     * @param description The description of the event.
     * @param isDone      The indication of the event being marked.
     * @param from        The starting datetime of the event.
     * @param to          The ending datetime of the event.
     */
    public Event(String description, boolean isDone, LocalDateTime from, LocalDateTime to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String encode() {
        return String.format("E | %d | %s | %s-%s", this.isDone ? 1 : 0,
                this.description, this.from.format(DateTimeFormatter.ofPattern(
                        Parser.STORAGE_DATE_TIME_PATTERN)),
                this.to.format(DateTimeFormatter.ofPattern(
                        Parser.STORAGE_DATE_TIME_PATTERN)));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + this.from.format(DateTimeFormatter.ofPattern(
                Parser.OUTPUT_DATE_TIME_PATTERN)) + " to: " + this.to.format(
                DateTimeFormatter.ofPattern(Parser.OUTPUT_DATE_TIME_PATTERN)) + ")";
    }
}
