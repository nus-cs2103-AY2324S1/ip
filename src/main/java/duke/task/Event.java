package duke.task;

import java.time.LocalDateTime;

/**
 * Represents an event to be used by the Duke chat-bot.
 */
public class Event extends Task {
    protected LocalDateTime fromTime;
    protected LocalDateTime toTime;

    /**
     * Creates an Event object.
     *
     * @param description The description of the event.
     * @param fromTime    The start time of the event.
     * @param toTime      The end time of the event.
     */
    public Event(String description, LocalDateTime fromTime, LocalDateTime toTime) {
        super(description, false);
        this.fromTime = fromTime;
        this.toTime = toTime;
    }

    /**
     * Creates an Event object with done status as specified.
     *
     * @param description The description of the event.
     * @param isDone      Whether the event is done.
     * @param fromTime    The start time of the event.
     * @param toTime      The end time of the event.
     */
    public Event(String description, boolean isDone, LocalDateTime fromTime, LocalDateTime toTime) {
        super(description, isDone);
        this.fromTime = fromTime;
        this.toTime = toTime;
    }

    /**
     * Returns the string representation of the event.
     *
     * @return The string representation of the event.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s | to: %s)", super.toString(),
                this.fromTime.format(DATE_TIME_FORMAT), this.toTime.format(DATE_TIME_FORMAT));
    }

    /**
     * Returns the event encoded for storage for the Duke chat-bot.
     *
     * @return The event encoded for storage for the Duke chat-bot.
     */
    @Override
    public String encodeTask() {
        return String.format("E;%s;%s;%s;%s", this.isDone ? "X" : " ", this.description, this.fromTime, this.toTime);
    }
}
