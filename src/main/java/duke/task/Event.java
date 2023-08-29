package duke.task;

import java.time.LocalDateTime;

/**
 * Represents an event to be used by the Duke chat-bot.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Creates an Event object.
     *
     * @param description The description of the event.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description, false);
        this.from = from;
        this.to = to;
    }

    /**
     * Creates an Event object with done status as specified.
     *
     * @param description The description of the event.
     * @param isDone      Whether the event is done.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     */
    public Event(String description, boolean isDone, LocalDateTime from, LocalDateTime to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the string representation of the event.
     *
     * @return The string representation of the event.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s | to: %s)", super.toString(),
                this.from.format(DATE_TIME_FORMAT), this.to.format(DATE_TIME_FORMAT));
    }

    /**
     * Returns the event encoded for storage for the Duke chat-bot.
     *
     * @return The event encoded for storage for the Duke chat-bot.
     */
    @Override
    public String encodeTask() {
        return String.format("E;%s;%s;%s;%s", this.isDone ? "X" : " ", this.description, this.from, this.to);
    }
}
