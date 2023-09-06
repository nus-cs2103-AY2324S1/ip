package todoify.taskmanager.task;

import todoify.util.EpochConverter;

/**
 * An event task. It tracks a title and the range associated with the event (from date-time to date-time), and can be
 * marked as completed.
 */
public class Event extends Task {

    /**
     * Starting timestamp of the event as Unix epoch in seconds.
     *
     * <p>
     * This is intentionally using the object instead of the primitive type to allow for Gson to set to null, flagging
     * the value as missing.
     * </p>
     */
    private final Long startTimestamp;

    /**
     * Ending timestamp of the event as Unix epoch in seconds.
     *
     * <p>
     * This is intentionally using the object instead of the primitive type to allow for Gson to set to null, flagging
     * the value as missing.
     * </p>
     */
    private final Long endTimestamp;

    /**
     * Constructor for an Event task.
     *
     * @param title          The title of the event.
     * @param startTimestamp The start of the event as Unix epoch in seconds.
     * @param endTimestamp   The end of the event as Unix epoch in seconds.
     */
    public Event(String title, long startTimestamp, long endTimestamp) {
        super(title);
        this.startTimestamp = startTimestamp;
        this.endTimestamp = endTimestamp;
    }

    /**
     * Obtains the starting timestamp of this event task.
     *
     * @return The starting timestamp as seconds since Unix epoch (1970-01-01 00:00:00 UTC).
     */
    public long getStartTimestamp() {
        return this.startTimestamp;
    }

    /**
     * Obtains the ending timestamp of this event task.
     *
     * @return The ending timestamp as seconds since Unix epoch (1970-01-01 00:00:00 UTC).
     */
    public long getEndTimestamp() {
        return this.endTimestamp;
    }

    @Override
    public String toString() {
        return String.format(
                "<E> %s %s (from: %s, to: %s)",
                this.getCompletedIndicatorString(),
                this.getTitle(),
                this.startTimestamp == null
                        ? null
                        : EpochConverter.getUserReadableDateTimeStringFromEpoch(this.getStartTimestamp()),
                this.endTimestamp == null
                        ? null
                        : EpochConverter.getUserReadableDateTimeStringFromEpoch(this.getEndTimestamp())
        );
    }
}
