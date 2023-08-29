package duke.task;

import java.time.LocalDateTime;

/**
 * Represents a deadline to be used by the Duke chat-bot.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Creates a Deadline object.
     *
     * @param description The description of the deadline.
     * @param by          The deadline of the deadline.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description, false);
        this.by = by;
    }

    /**
     * Creates a Deadline object with done status as specified.
     *
     * @param description The description of the deadline.
     * @param isDone      Whether the deadline is done.
     * @param by          The deadline of the deadline.
     */
    public Deadline(String description, boolean isDone, LocalDateTime by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Returns the string representation of the deadline.
     *
     * @return The string representation of the deadline.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.by.format(DATE_TIME_FORMAT));
    }

    /**
     * Returns the deadline encoded for storage for the Duke chat-bot
     *
     * @return The deadline encoded for storage for the Duke chat-bot.
     */
    @Override
    public String encodeTask() {
        return String.format("D;%s;%s;%s", this.isDone ? "X" : " ", this.description, this.by);
    }
}
