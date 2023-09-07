package todoify.taskmanager.task;

import todoify.util.EpochConverter;

/**
 * A deadline task. It tracks a title and a deadline associated with it, and can be marked as completed.
 */
public class Deadline extends Task {

    /**
     * Deadline timestamp of the event as Unix epoch in seconds.
     *
     * <p>
     * This is intentionally using the object instead of the primitive type to allow for Gson to set to null, flagging
     * the value as missing.
     * </p>
     */
    private final Long deadline;

    /**
     * Constructor for a deadline task.
     *
     * @param title    The title of the deadline.
     * @param deadline The deadline, as Unix epoch in seconds.
     */
    public Deadline(String title, long deadline) {
        super(title);
        this.deadline = deadline;
    }

    /**
     * Obtains the deadline of this task.
     *
     * @return The deadline of this task as seconds since Unix epoch (1970-01-01 00:00:00 UTC).
     */
    public long getDeadline() {
        return this.deadline;
    }

    @Override
    public String toString() {
        return String.format(
                "<D> %s %s (by: %s)",
                this.getCompletedIndicatorString(),
                this.getTitle(),
                this.deadline == null ? null : EpochConverter.getUserReadableDateTimeStringFromEpoch(this.getDeadline())
        );
    }
}
